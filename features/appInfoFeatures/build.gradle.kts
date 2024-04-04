plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.appinfofeatures"

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
    coroutines()
    compose()
    androidLifeCycle()
    androidTests()
    coil()

    designsystem()
    common()
    domain()
    entity()
    apiresponse()
}