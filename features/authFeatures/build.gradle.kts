plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.authfeatures"
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
    implementation ("com.google.android.gms:play-services-auth:20.2.0")

    designsystem()
    common()
    domain()
    entity()
    apiresponse()
    data()
}