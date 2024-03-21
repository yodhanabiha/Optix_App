plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.nabiha.data"
}

dependencies {
    room()
    retrofit()
    hilt()
    domain()
    apiresponse()
    entity()
    di()
    datastore()
}