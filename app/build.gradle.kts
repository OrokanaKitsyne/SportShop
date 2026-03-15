plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.sportshop"
<<<<<<< HEAD
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sportshop"
        minSdk = 26
=======
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sportshop"
        minSdk = 24
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
<<<<<<< HEAD
        kotlinCompilerExtensionVersion = "1.5.11"
=======
        kotlinCompilerExtensionVersion = "1.5.1"
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
<<<<<<< HEAD
=======
    implementation(libs.androidx.datastore.core.jvm)
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

<<<<<<< HEAD
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
=======
    //Supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.4"))

    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:auth-kt")
    implementation("io.github.jan-tennert.supabase:realtime-kt")
    implementation("io.github.jan-tennert.supabase:storage-kt")

    implementation("io.ktor:ktor-client-android:2.3.7")


>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")


    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.6")

<<<<<<< HEAD
    implementation("androidx.core:core-splashscreen:1.0.1")
=======

>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
}