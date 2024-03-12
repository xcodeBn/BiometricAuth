# BioMetricAuth Android Library

## Introduction

BioMetricAuth is an Android library designed to simplify the implementation of biometric authentication in Android applications. It provides a helper class with methods to check the availability of biometric authentication on the device and prompt the user for biometric authentication using the BiometricPrompt API.

## Features

- Check the availability of biometric authentication on the device.
- Prompt the user for biometric authentication with a customizable dialog.
- Handle various states of biometric authentication gracefully.

## Installation

#### Step 1. Add the JitPack repository to your build file
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
#### Step 2. Add the dependency

```
	dependencies {
	        implementation 'com.github.xcodeBn:BiometricAuth:1.0.2'
	}
```



[![](https://jitpack.io/v/xcodeBn/BiometricAuth.svg)](https://jitpack.io/#xcodeBn/BiometricAuth)

