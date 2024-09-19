import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin.Companion.isIncrementalKapt

plugins {
    alias(libs.plugins.android.application)
    id("io.realm.kotlin")
  //  id("realm-android")
    id("kotlin-android")

    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.dagger.hilt)
   // alias(libs.plugins.google.services)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.jetbrains.kotlin.compose)
//    id 'kotlin-kapt'
}

android {
    namespace = "com.example.multimodule"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.multimodule"
        minSdk = 26
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
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    configurations {
        create("cleanedAnnotations")
        implementation {
            exclude(group = "org.jetbrains", module = "annotations")
        }
    }
}

dependencies {

    implementation (libs.androidx.core.ktx)
            implementation (libs.androidx.lifecycle.runtime.ktx)
            implementation (libs.androidx.activity.compose)
            implementation (libs.androidx.ui)
            implementation (libs.androidx.ui.tooling.preview)
            implementation (libs.androidx.material3)
            testImplementation (libs.junit)
            androidTestImplementation (libs.androidx.junit)
            androidTestImplementation (libs.androidx.espresso.core)
            androidTestImplementation (libs.androidx.ui.test.junit4)
            debugImplementation (libs.androidx.ui.tooling)
            debugImplementation (libs.androidx.ui.test.manifest)

            // Compose Navigation
            implementation (libs.androidx.navigation.compose)

            // Firebase
            implementation (libs.firebase.auth.ktx)
            implementation (libs.firebase.storage.ktx)

            // Room components
            implementation (libs.androidx.room.runtime)
            ksp (libs.androidx.room.compiler)
            implementation (libs.androidx.room.ktx)

            // Runtime Compose
            implementation (libs.androidx.lifecycle.runtime.compose)

            // Splash API
            implementation (libs.androidx.core.splashscreen)

            // Mongo DB Realm
            implementation (libs.kotlinx.coroutines.core)
            implementation (libs.library.sync)

            // Dagger Hilt
            implementation (libs.hilt.android)
            ksp (libs.hilt.compiler)
            implementation (libs.androidx.hilt.navigation.compose)

            // Coil
            implementation (libs.coil.compose)

            // Pager - Accompanist [DEPRECATED]
//    implementation "com.google.accompanist:accompanist-pager:0.27.0"

            // Date-Time Picker
            implementation (libs.core)

            // CALENDAR
            implementation (libs.calendar)

            // CLOCK
            implementation (libs.clock)

            // Message Bar Compose
            implementation (libs.messagebarcompose)

            // One-Tap Compose
            implementation (libs.onetapcompose)

            // Desugar JDK
            coreLibraryDesugaring (libs.desugar.jdk.libs)
}


