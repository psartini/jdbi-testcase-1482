import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jdbi_version = "3.6.0"
val kotlin_version = "1.3.20"

plugins {
    java
    kotlin("jvm") version "1.3.20"
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            languageVersion = "1.3"
            apiVersion = "1.3"
        }
    }
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "UTF-8"
    }
    withType<Test> {
        useJUnitPlatform {
            includeEngines
                    .plus("junit-jupiter")
        }
        reports {
            junitXml.destination = File(buildDir.path + "/test-results/")
        }
        testLogging {
            events.addAll(listOf(
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.FAILED))
            exceptionFormat = TestExceptionFormat.SHORT
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }
}

dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.zaxxer:HikariCP:2.7.8")

    implementation("org.jdbi:jdbi3-core:$jdbi_version")
    implementation("org.jdbi:jdbi3-sqlobject:$jdbi_version")
    implementation("org.jdbi:jdbi3-postgres:$jdbi_version")
    implementation("org.jdbi:jdbi3-kotlin:$jdbi_version") {
        exclude("org.jetbrains.kotlin")
    }
    implementation("org.jdbi:jdbi3-kotlin-sqlobject:$jdbi_version") {
        exclude("org.jetbrains.kotlin")
    }
    implementation("org.postgresql:postgresql:42.2.1")

    // logging
    implementation("org.slf4j:slf4j-jdk14:1.7.25")

    // test
    testImplementation("org.junit.platform:junit-platform-engine:1.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")

    testImplementation("io.mockk:mockk:1.8.13.kotlin13")

    testImplementation("com.opentable.components:otj-pg-embedded:0.11.3")
}

repositories {
    jcenter()
}