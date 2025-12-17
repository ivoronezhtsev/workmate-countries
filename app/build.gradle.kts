plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.workmatecountries"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.workmatecountries"
        minSdk = 24
        targetSdk = 36
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    val roomVersion = "2.8.4"
    // Room
    implementation("androidx.room:room-runtime:$roomVersion")

    // Kotlin extensions + Coroutines support
    implementation("androidx.room:room-ktx:$roomVersion")

    // Annotation processor (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")


    val coroutinesVersion = "1.8.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // Конвертер JSON (Gson)
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    // Compose ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Основные Compose библиотеки
    implementation( "androidx.compose.ui:ui:1.5.0")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation( "androidx.compose.ui:ui-tooling-preview:1.5.0")
    debugImplementation( "androidx.compose.ui:ui-tooling:1.5.0")

    // Если используете LiveData с Compose
    implementation( "androidx.compose.runtime:runtime-livedata:1.5.0")
}