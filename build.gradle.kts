// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    dependencies {
        // Right now this classpath is needed for compatibility of the
        // MongoDB Realm with Kotlin 2.0
        classpath(libs.realm.kotlin.gradle.plugin)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.dagger.hilt) apply false
  //  alias(libs.plugins.google.services) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false

 //   alias(libs.plugins.google)
//    id 'io.realm.kotlin' version '1.16.0' apply false
}
