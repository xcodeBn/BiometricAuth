@file:Suppress("DEPRECATION")

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    `maven-publish`

}

android {
    namespace = "com.pisces.piauth"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.androidx.biometric)
    implementation(libs.androidx.biometric.v120alpha05)
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            groupId = "com.pisces.piauth"
            artifactId = "piauth"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}