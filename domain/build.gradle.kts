plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.domain"
}

dependencies {
    room()
    retrofit()
    hilt()
    coroutines()

    entity()
    apiresponse()
}