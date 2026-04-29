_default:
  just --list

rebuild-classes-jar:
  # This seems to help `kotlin-lsp` 🤔 (it logs a warning when it needs it!)
  gradle :app:bundleDebugClassesToCompileJar

# Build debug APK
build:
  gradle assembleDebug

# Build and install debug APK on connected device/emulator
install:
  gradle installDebug

# Run all tests
test:
  gradle test

# Clean build artifacts
clean:
  gradle clean

# Run linter (via ktlint)
lint:
  ktlint

# Run formatter (via ktlint)
fmt:
  ktlint -F
