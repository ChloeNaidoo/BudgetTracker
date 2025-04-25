plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "vcmsa.projects.budgettracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "vcmsa.projects.budgettracker"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("androidx.room:room-runtime:2.7.0") // Use the latest version
    implementation("androidx.room:room-ktx:2.7.0") // Kotlin extensions for Room (optional, but recommended)

    implementation("io.coil-kt:coil:3.0.0-alpha05") // Or use Glide: implementation("com.github.bumptech.glide:glide:4.16.0")
    // If using Glide, you might also need an annotation processor:
    // kapt("com.github.bumptech.glide:compiler:4.16.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-alpha01") // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0-alpha01")    // LiveData

    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")
    implementation("androidx.core:core-ktx:1.13.0-alpha02")
    implementation("com.google.android.material:material:1.12.0-alpha03")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}