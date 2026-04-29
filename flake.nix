{
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs = { self, nixpkgs }: let
    # Support multiple systems
    systems = [ "x86_64-linux" "aarch64-darwin" ];
    forAllSystems = nixpkgs.lib.genAttrs systems;
  in {
    devShells = forAllSystems (system: let
      pkgs = import nixpkgs {
        inherit system;
        config.allowUnfree = true;
        config.android_sdk.accept_license = true;
      };

      # NOTE: SDK 34 is Android 14
      # More versions at <https://en.wikipedia.org/wiki/Android_version_history#Overview>
      buildToolsVersion = "34.0.0";
      androidSdk = pkgs.androidenv.composeAndroidPackages {
        platformVersions = [ "34" "35" ];
        # Build tools (aapt2, apksigner, …) are versioned independently from the platform.
        buildToolsVersions = [ buildToolsVersion ];
        cmdLineToolsVersion = "latest"; # https://developer.android.com/tools/

        # Disable the emulator, not needed as I test on my device directly
        includeEmulator = false;
        includeSystemImages = false; # (only needed for the emulator)
        systemImageTypes = [ "default" ]; # default: bare AOSP, no Google services
        # ABI of the emulator, should match host's architecture.
        abiVersions = if system == "aarch64-darwin" then [ "arm64-v8a" ] else [ "x86_64" ];
      };
      jdk = pkgs.jdk17;
      aapt2_bin = "${androidSdk.androidsdk}/libexec/android-sdk/build-tools/${buildToolsVersion}/aapt2";
    in {
      default = pkgs.mkShell {
        buildInputs = [
          androidSdk.androidsdk
          jdk
          pkgs.gradle
          pkgs.kotlin

          pkgs.ktlint # linter + formatter
          # LSP: https://github.com/Kotlin/kotlin-lsp
          # NOTE: It doesn't really have good Android projects support yet..
          # see: https://github.com/Kotlin/kotlin-lsp/issues/26
          # see: https://github.com/Kotlin/kotlin-lsp/issues/97
          (pkgs.callPackage ./nix/kotlin-lsp.nix {})
          # note: The LSP is quite slow to start, easily 30s before first proof of life
        ];

        ANDROID_HOME = "${androidSdk.androidsdk}/libexec/android-sdk";
        ANDROID_SDK_ROOT = "${androidSdk.androidsdk}/libexec/android-sdk";
        JAVA_HOME = "${jdk.home}";

        # Force Gradle to use the Nix-patched binary (so it doesn't DL its own version!)
        GRADLE_OPTS = "-Dorg.gradle.project.android.aapt2FromMavenOverride=${aapt2_bin}";
      };
    });
  };
}
