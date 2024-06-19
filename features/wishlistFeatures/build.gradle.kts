plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.wishlistfeatures"
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
    implementation(project(":features:homeFeatures"))

    designsystem()
    common()
    domain()
    entity()
    apiresponse()
    data()
}