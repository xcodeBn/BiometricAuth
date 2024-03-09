# BioMetricAuth Android Library

## Introduction

BioMetricAuth is an Android library designed to simplify the implementation of biometric authentication in Android applications. It provides a helper class with methods to check the availability of biometric authentication on the device and prompt the user for biometric authentication using the BiometricPrompt API.

## Features

- Check the availability of biometric authentication on the device.
- Prompt the user for biometric authentication with a customizable dialog.
- Handle various states of biometric authentication gracefully.

## Installation

To use BioMetricAuth in your Android project, follow these steps:

1. Add the JitPack repository to your project's build.gradle file:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
2. Add the dependency in your app's build.gradle file:

```groovy
dependencies {
    implementation 'com.github.YourUsername:YourLibrary:Tag'
}
Replace `YourUsername` with your GitHub username and `YourLibrary` with the name of your library repository. Replace `Tag` with the desired version tag, e.g., `1.0`.

## Usage

### Initialization

First, initialize the BioMetricAuth instance with the application context:

```kotlin
val bioMetricAuth = BioMetricAuth(context)
