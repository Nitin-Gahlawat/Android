plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.fetchbox"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.fetchbox"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        dataBinding=true
    }
}

dependencies {

    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")  // Optional, for JSON conversion using Gson
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")


    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android) // Use ksp instead of kapt

    // If using ViewModel or LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")


    debugImplementation ("com.github.chuckerteam.chucker:library:4.1.0")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:4.1.0")

    implementation(libs.androidx.room.ktx)
    ksp("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

apply(plugin = "dagger.hilt.android.plugin")
