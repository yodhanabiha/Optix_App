plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.wishlistfeatures"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    room()
    androidTests()
    retrofit()
    hilt()
    coroutines()
    compose()
    accompanist()
    androidLifeCycle()
    coil()
    features()


    designsystem()
    common()
    data()
}