plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.profilefeatures"
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

    designsystem()
    common()
    domain()
    entity()
    apiresponse()
    data()
}