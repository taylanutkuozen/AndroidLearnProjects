// build.gradle.kts (:app modülü)

plugins {
    alias(libs.plugins.androidApplication) // <-- Burası artık doğru
    alias(libs.plugins.jetbrainsKotlinAndroid) // <-- Burası artık doğru
    // Safe Args ve KSP için de alias kullanabiliriz:
    alias(libs.plugins.navigationSafeArgsKotlin)
    alias(libs.plugins.googleDevtoolsKsp)
}

android {
    namespace = "com.example.learningcalories"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.learningcalories"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        // Kotlin 1.9.0 kullanıyorsanız, jvmTarget'ı 1.9'a çıkarmanız önerilir.
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8" // Eğer Kotlin versiyonunuz 1.9.x ise, burayı da "1.9" yapmanız önerilir.
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout) // libs.versions.toml'a eklediyseniz
    implementation(libs.androidx.preference.ktx)     // libs.versions.toml'a eklediyseniz
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.navigation.fragment.ktx) // libs.versions.toml'dan çağırın
    implementation(libs.navigation.ui.ktx)      // libs.versions.toml'dan çağırın

    // Room
    implementation(libs.room.runtime)        // libs.versions.toml'dan çağırın
    annotationProcessor(libs.room.compiler) // libs.versions.toml'dan çağırın
    ksp(libs.room.compiler)                 // libs.versions.toml'dan çağırın

    implementation(libs.kotlinx.coroutines.android) // libs.versions.toml'dan çağırın
    implementation(libs.room.ktx)                   // libs.versions.toml'dan çağırın

    // Lifecycle
    implementation(libs.lifecycle.viewmodel.ktx)   // libs.versions.toml'dan çağırın
    implementation(libs.lifecycle.common.java8)    // libs.versions.toml'dan çağırın
    implementation(libs.lifecycle.livedata.ktx)     // libs.versions.toml'dan çağırın

    // Retrofit
    implementation(libs.retrofit)               // libs.versions.toml'dan çağırın
    implementation(libs.retrofit.converter.gson) // libs.versions.toml'dan çağırın

    // Glide
    implementation(libs.glide)                  // libs.versions.toml'dan çağırın
}