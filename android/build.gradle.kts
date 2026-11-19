// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // ponytail: pins the KGP used by AGP's built-in Kotlin; firebase-auth 25 needs Kotlin 2.4 metadata
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}