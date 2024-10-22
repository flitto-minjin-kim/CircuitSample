plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.minjin.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.minjin.sample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    // compose
    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3.core)
    implementation(libs.androidx.compose.material3)
    // hilt
    implementation(libs.androidx.hilt.common)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // kotlin
    implementation(libs.kotlinx.collections.immutable)

    // circuit
    implementation(libs.bundles.circuit)
    api(libs.circuit.codegen.annotation)
    ksp(libs.circuit.codegen.ksp)
}