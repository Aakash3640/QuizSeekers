// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin.v210) // Ensure it matches your Kotlin version
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.56.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
}



