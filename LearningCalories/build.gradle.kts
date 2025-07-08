// build.gradle.kts (Root Project)

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Bu plugin'ler genellikle modül seviyesinde apply edilecek olanlar ve burada 'apply false' ile tanımlanır.
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}