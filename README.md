# Noize

A fast, ad-free, open-source YouTube Music client for Android. Built with Kotlin and Jetpack Compose, powered by AndroidX Media3/ExoPlayer, Room, WorkManager, and Koin.

- **License**: GPL-3.0
- **Package ID**: `com.noize.app`
- **Flavors**: `foss` (no trackers), `full` (includes Sentry crash reports)

## Features
- **Playback**: Background playback, caching, crossfade, 1080p video option, Android Auto
- **Catalog**: Home, Charts, Podcasts, Moods & Genres, full-text search
- **Lyrics**: Musixmatch, LRCLIB, Spotify; optional AI translation (OpenAI/Gemini)
- **Integrations**: SponsorBlock, Return YouTube Dislike, Spotify Canvas
- **Personalization**: Listening history analytics, custom playlists, multi-account
- **Widgets**: App Widget and Android Auto

## Tech Stack
- Kotlin, Jetpack Compose (Material 3, Navigation)
- AndroidX Media3/ExoPlayer (`MediaLibraryService`)
- Koin for dependency injection
- Room (with auto-migrations), DataStore Preferences
- OkHttp, Coil 3
- WorkManager
- Modules: `lyricsProviders`, `lyricsService`, `kotlinYtmusicScraper`, external `MediaServiceCore` (submodule)

## Project Structure
- Application module: `app/`
- Supporting modules: `aiService/`, `spotify/`, `lyricsProviders/`, `lyricsService/`, `kotlinYtmusicScraper/`
- External submodule: `MediaServiceCore/` (with `SharedModules/`)

## Build and Run
### Android Studio
1. Open the project in Android Studio
2. Let Gradle sync
3. Choose a build variant:
   - `fossDebug` for tracker-free builds
   - `fullDebug` if you need Sentry (requires config)
4. Run on device/emulator

### Gradle (CLI)
```bash
./gradlew :app:assembleFossDebug
```
APK outputs in `app/build/outputs/apk/foss/debug/`:
- `app-foss-arm64-v8a-debug.apk` (most modern phones)
- `app-foss-armeabi-v7a-debug.apk` (older 32‑bit ARM)
- `app-foss-x86_64-debug.apk` (emulators)
- `app-foss-universal-debug.apk` (all ABIs; larger)

## Configuration
- **AI Lyrics Translation (optional)**: Provide OpenAI or Gemini API keys via in-app settings
- **Updates**: "What’s new" popups are disabled; distribute updated APKs directly
 
 ## Privacy    
- `foss` flavor contains no trackers or third‑party servers
- If users enable YouTube’s “Send back to Google”, listening history may be sent to improve recommendations

## Localization
Multi-language UI is supported. Contributions for translations are welcome.

## Contributing
Issues and pull requests are welcome. Please keep discussions constructive.

## License
GPL-3.0 — see `LICENSE`.

## Acknowledgements
- InnerTune and SmartTube for inspiration and technical ideas
- SponsorBlock and Return YouTube Dislike integrations
