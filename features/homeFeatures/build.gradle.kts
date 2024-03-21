plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.homefeatures"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    androidTests()
    room()
    retrofit()
    hilt()
    coroutines()
    compose()
    accompanist()
    androidLifeCycle()


    designsystem()
    common()
    data()
}