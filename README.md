# SwissBorg challenge

## Goal

Create a crypto marketplace screen that displays a list of crypto trading pairs with USD.

## Features

- Price updates periodical (every 5 seconds).
- Information on which coins allow yielding.
- Coins icons.
- Offline first, data is persisted.
- Connectivity monitoring, only tries to fetch data when there's connectivity.

## Arquitecture

Clean Arquitecture with MVVM.

## Notes

Only tested in Android.  
No API request throttling. Although not complex to implement, it was considered outside the
scope of
this exercise, as per the requirements we are implicitly respecting API limits.

## Kotlin Multiplatform

This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…