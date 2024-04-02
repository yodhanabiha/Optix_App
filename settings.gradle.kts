pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "optix"
include(":app")
include(":designsystem")
include(":features:homeFeatures")
include(":common")
include(":di")
include(":domain")
include(":model:entity")
include(":model:apiresponse")
include(":data")
include(":features:authFeatures")
include(":features:cartFeatures")
include(":features:cartFeatures")
include(":features:wishlistFeatures")
