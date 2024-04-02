import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hiltNavCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val rxJava3adapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val javapoet = "com.squareup:javapoet:${Versions.javapoet}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val jUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val jUnitTestUi = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val viewModelSaveState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val runtimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.runtimeCompose}"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}"

    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val accompanistPagerIndicator= "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
}

fun DependencyHandler.accompanist(){
    implementation(Dependencies.accompanistPager)
    implementation(Dependencies.accompanistSystemUiController)
    implementation(Dependencies.accompanistPagerIndicator)
}
fun DependencyHandler.datastore(){
    implementation(Dependencies.dataStore)
}
fun DependencyHandler.coil() {
    implementation(Dependencies.coil)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.kotlinCoroutines)
}

fun DependencyHandler.leakcanary() {
    debugImplementation(Dependencies.leakcanary)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.timber)
    implementation(Dependencies.gson)
    implementation(Dependencies.rxJava3adapter)
    implementation(Dependencies.retrofitGsonConverter)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeNavigation)
    debugImplementation(Dependencies.composeUiToolingPreview)
}

fun DependencyHandler.androidTests() {
    implementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.jUnitTestUi)
    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espresso)
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.composeTestManifest)
}

fun DependencyHandler.androidLifeCycle() {
    implementation(Dependencies.viewModel)
    implementation(Dependencies.liveData)
    implementation(Dependencies.runtimeCompose)
    implementation(Dependencies.viewModelSaveState)
    implementation(Dependencies.lifecycleService)
}


fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltNavCompose)
    implementation(Dependencies.javapoet)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.app() {
    implementation(project(":app"))
}

fun DependencyHandler.di() {
    implementation(project(":di"))
}

fun DependencyHandler.domain() {
    implementation(project(":domain"))
}

fun DependencyHandler.data() {
    implementation(project(":data"))
}

fun DependencyHandler.entity() {
    implementation(project(":model:entity"))
}

fun DependencyHandler.designsystem() {
    implementation(project(":designsystem"))
}

fun DependencyHandler.common() {
    implementation(project(":common"))
}

fun DependencyHandler.apiresponse() {
    implementation(project(":model:apiresponse"))
}

fun DependencyHandler.features() {
    implementation(project(":features:homeFeatures"))
    implementation(project(":features:authFeatures"))
    implementation(project(":features:wishlistFeatures"))
    implementation(project(":features:cartFeatures"))
}

