plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

//    Dagger hilt and android anotation processing
//    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

//    Ksp
    id("com.google.devtools.ksp")

//    Katpt
    id("kotlin-kapt")

}

android {
    namespace = "com.example.imageshare"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.imageshare"
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {

//        Adding Version 8 for dagger hilt
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

//    kapt {
//        correctErrorTypes = true
//    }

//    BuildFeatures
    buildFeatures {
        dataBinding = true
//        viewBinding=true
    }

}

dependencies {

//Dagger hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation(libs.androidx.camera.core)
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")


//Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")


//    ViewModel Scope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")


    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")


    // GSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

//    Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

//Default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

//    kapt("androidx.databinding:databinding-compiler:7.2.2")
}