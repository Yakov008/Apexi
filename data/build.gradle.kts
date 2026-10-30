// Android library: local database (Room) and bundled content. Room is added in step M2.
plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "io.github.yakov008.apexi.data"
    compileSdk = 37

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":domain"))
}
