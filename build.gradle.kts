// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra["kotlin_version"] = "1.3.60-eap-25"
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-alpha05")
        classpath(kotlin("gradle-plugin", version = "1.3.60-eap-25"))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}