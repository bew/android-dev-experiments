_default:
  just --list

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
