# WordDay - Android Vocabulary App

A modern Android app built with Jetpack Compose that helps expand vocabulary by presenting one English word of the day with comprehensive information.

## Features

- **Word of the Day**: Displays an English word with Russian translation
- **Example Sentences**: Two example sentences in both English and Russian
- **Pronunciation**: Phonetic transcription with audio playback capability
- **Additional Meanings**: List of alternative translations
- **Swipe Navigation**: Vertical pager for easy navigation between pages

## Architecture

The app follows a simple two-layer architecture:

- **Data Layer**: Contains the word model and mock repository
- **Presentation Layer**: UI components and ViewModel for state management

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with ViewModel
- **State Management**: StateFlow
- **Audio**: MediaPlayer for local MP3 playback
- **Navigation**: Accompanist Pager for swipe navigation

## Project Structure

```
app/src/main/java/com/sobolev/wocab/
├── data/
│   ├── WordModel.kt          # Data class for word information
│   └── WordRepository.kt     # Mock repository with hardcoded data
├── presentation/
│   ├── WordViewModel.kt      # ViewModel for state management
│   ├── WordDayScreen.kt      # Main UI screen with pager
│   └── AudioPlayer.kt        # Utility for audio playback
└── MainActivity.kt           # Main activity entry point
```

## Setup Instructions

1. Clone the repository
2. Open in Android Studio
3. Add an MP3 audio file to `app/src/main/res/raw/` named `serendipity.mp3`
4. Build and run the app

## Audio Requirements

- Place MP3 files in the `res/raw/` directory
- Filename should match the `audioFileName` property in `WordRepository`
- Audio should contain the pronunciation of the English word

## Current Word

The app currently features the word "Serendipity" with:
- English: Serendipity
- Russian: Счастливая случайность
- Phonetic: /ˌserənˈdɪpəti/
- Examples and additional meanings

## Future Enhancements

- Network integration for dynamic word fetching
- Local database for word history
- Multiple language support
- Word difficulty levels
- Progress tracking
- Offline mode

## Dependencies

- Jetpack Compose BOM
- Material3 Design System
- ViewModel Compose
- Accompanist Pager
- Core Android libraries 