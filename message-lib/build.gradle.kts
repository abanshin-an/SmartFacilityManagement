plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management") version "1.1.7"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "ru.a2n.sfm"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

tasks {
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.junit.jupiter:junit-jupiter-api")
//    testImplementation("org.junit.jupiter:junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
}