plugins {
    kotlin("jvm") version "2.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.kotlin.test)
    testImplementation(libs.strikt.core)
    testImplementation(libs.hamcrest.all)

    testImplementation(libs.junit.platform.suite)
    testImplementation(libs.cucumber.junit.platform.engine)
    testImplementation(libs.cucumber.java)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(24)
}