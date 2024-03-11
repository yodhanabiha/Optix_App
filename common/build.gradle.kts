plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.common"
}

dependencies {
    room()
    retrofit()
    hilt()
}