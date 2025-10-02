plugins {
    kotlin("jvm") version "2.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.strikt:strikt-core:0.34.0")
    testImplementation("org.hamcrest:hamcrest-all:1.3")

    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.30.0")
    testImplementation("io.cucumber:cucumber-java:7.30.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}