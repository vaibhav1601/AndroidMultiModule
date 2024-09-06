plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.android.realm)
   // alias(libs.plugins.google.service)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)


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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Compose Navigation
    implementation (libs.androidx.navigation.compose)

    //Firebase
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.storage.ktx)

    //Room database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.compiler.processing)
    implementation(libs.androidx.room.ktx)

    //Run time compose
    implementation(libs.androidx.lifecycle.runtime.compose)

    //Splash
    implementation(libs.androidx.core.splashscreen)

    //mango db Realm

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt"){
        version{
            strictly("1.6.0-native-mt")
        }
    }
    implementation(libs.library.sync)


    //dagger Hilt
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)

    //Google Auth

    implementation(libs.play.services.auth)

    //Coil
    implementation(libs.coil.compose)

    //Pager
    implementation(libs.accompanist.pager)

    //datetime
    implementation(libs.datetime)

    //message Bar Compose
    implementation(libs.messagebarcompose)

    //One Tap Compose
    implementation(libs.onetapcompose)

    implementation(libs.desugar.jdk.libs)
 //   implementation(libs.google.services)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}