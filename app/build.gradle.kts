import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
}

val getPropertiesFile: (String) -> Properties = { path ->
    val properties = Properties()
    properties.load(FileInputStream(file(path)))
    properties
}

android {
    namespace = "com.robi.mdb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.robi.mdb"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            getPropertiesFile("endpoint.properties").forEach{
                buildConfigField(type = "String", name = it.key.toString(), value = it.value.toString())
            }
        }

        getByName("release") {
            getPropertiesFile("endpoint.properties").forEach{
                buildConfigField(type = "String", name = it.key.toString(), value = it.value.toString())
            }
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(libs.squareup.retrofit)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.google.material)

    implementation(libs.facebook.shimmer)
    implementation(libs.github.glide)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.github.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.espresso.core)
}
kapt {
    correctErrorTypes = true
    useBuildCache = true
}