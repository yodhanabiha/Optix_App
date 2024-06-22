plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.di"
}

dependencies {
    room()
    retrofit()
    hilt()
    common()
}