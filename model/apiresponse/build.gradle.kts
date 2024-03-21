plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.apiresponse"
}

dependencies {
    room()
    hilt()
}