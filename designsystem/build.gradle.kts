plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.designsystem"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    room()
    retrofit()
    hilt()
    compose()
    leakcanary()
    androidTests()
//    entity()
    androidLifeCycle()
    androidTests()
    coroutines()
}