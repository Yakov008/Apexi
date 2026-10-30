// Pure Kotlin module: training rules with no Android dependencies.
// Keeping it Android-free makes the rules fast to test and portable to Kotlin Multiplatform (iOS).
plugins {
    alias(libs.plugins.kotlin.jvm)
}

// Compile to Java 17 bytecode regardless of the JDK that runs Gradle.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit)
}
