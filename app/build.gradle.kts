import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.alex.droid.dev.app"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":router"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCompilerVersion.VERSION}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.50")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.browser:browser:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.fragment:fragment-ktx:1.2.0-rc01")
    implementation("com.google.android.material:material:1.2.0-alpha01")

    implementation("io.reactivex.rxjava2:rxjava:2.2.13")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("org.koin:koin-android:2.0.1")
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")

    implementation("android.arch.lifecycle:extensions:1.1.1")

    implementation("com.github.bumptech.glide:glide:4.9.0") {
        exclude("com.android.support")
    }
    kapt("com.github.bumptech.glide:compiler:4.9.0")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.12")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.mockito:mockito-core:3.0.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
