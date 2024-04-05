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
    retrofit()
    hilt()
    coroutines()
    compose()
    androidLifeCycle()
    androidTests()
    implementation(project(":features:appInfoFeatures"))

    designsystem()
    common()
    domain()
    entity()
    apiresponse()
}