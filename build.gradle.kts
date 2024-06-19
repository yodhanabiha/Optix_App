// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        flatDir {
            dirs ("${project(":unityLibrary").projectDir}/libs")
        }
    }

    dependencies {
        classpath(Dependencies.hiltAgp)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath ("com.squareup:javapoet:${Versions.javapoet}")
    }
}