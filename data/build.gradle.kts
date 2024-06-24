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
    common()
    di()
    datastore()
    implementation ("com.google.android.gms:play-services-auth:20.2.0")
}