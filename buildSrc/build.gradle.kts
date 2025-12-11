plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("io.papermc.paperweight.userdev:io.papermc.paperweight.userdev.gradle.plugin:2.0.0-beta.19")
    implementation("com.gradleup.shadow:com.gradleup.shadow.gradle.plugin:9.3.0")
}

repositories {
    gradlePluginPortal()
}
