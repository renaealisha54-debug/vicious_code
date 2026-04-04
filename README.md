# vicious_code
– Full-Stack Mobile IDE &amp; APK Builder
# ViciousCode – Full-Stack Mobile IDE & APK Builder

**The only Android app that actually lets you edit, fix, convert, build, compile, run, and export APK for EVERY major language — no simulations, no cloud, no excuses.**

## Features
(See the app description above — it all works.)

## Tech Stack
- Kotlin + Jetpack Compose
- Sora Editor + LSP4J
- Chaquopy, NDK, embedded OpenJDK, Metro/Hermes
- Dynamic Feature Modules for extensions

## Permissions Explained
- SYSTEM_ALERT_WINDOW → floating notepad overlay
- MANAGE_EXTERNAL_STORAGE → real APK export and file handling
- INTERNET → extension updates only (optional offline)

## How to Build & Run
1. Clone repo
2. Open in Android Studio Iguana or later
3. Sync Gradle (it will pull 800+ MB of runtimes — first build takes time)
4. Run on device (emulator won’t cut it for native compiles)
5. Grant overlay permission on first launch

## Extension Installation
Go to Extensions tab → browse → install. All extensions are signed and verified.

## Roadmap (already planned)
- iOS version (SwiftUI + same back-end)
- VS Code extension sync
- AI-assisted code conversion (local model option)

**Built by someone who doesn’t accept “it can’t be done on Android.”**

Pull requests welcome. Issues will be handled viciously.
