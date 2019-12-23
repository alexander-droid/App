import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
    id("kotlin-android-extensions")
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

    dataBinding.isEnabled = true
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":router"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCompilerVersion.VERSION}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${KotlinCompilerVersion.VERSION}")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.browser:browser:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.fragment:fragment-ktx:1.2.0-rc03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-rc03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-rc03")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-rc03")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("com.google.android.material:material:1.2.0-alpha02")

    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.6.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("io.reactivex.rxjava2:rxjava:2.2.13")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("com.google.android.libraries.places:places:2.1.0")

    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("org.koin:koin-android:2.0.1")
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")

    implementation("com.github.ihsanbal:LoggingInterceptor:3.0.0") {
        exclude("org.json', module: 'json")
    }
    implementation("com.github.bumptech.glide:glide:4.9.0") {
        exclude("com.android.support")
    }
    implementation("androidx.exifinterface:exifinterface:1.1.0")
    kapt("com.github.bumptech.glide:compiler:4.10.0")

    implementation("androidx.paging:paging-runtime-ktx:2.1.0")

    implementation("androidx.room:room-runtime:2.2.2")
    implementation("androidx.room:room-ktx:2.2.2")
    kapt("androidx.room:room-compiler:2.2.2")

    implementation("androidx.ui:ui-layout:0.1.0-dev02")
    implementation("androidx.ui:ui-material:0.1.0-dev02")
    implementation("androidx.ui:ui-tooling:0.1.0-dev02")

    implementation("com.facebook.stetho:stetho:1.5.1")

    testImplementation("junit:junit:4.12")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.mockito:mockito-core:3.1.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    implementation ("androidx.annotation:annotation:1.1.0")
}
