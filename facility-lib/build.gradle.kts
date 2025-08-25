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

val springBootDependencies: String by rootProject.extra
val springCloudGateway: String by rootProject.extra
val lombok: String by rootProject.extra

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springBootDependencies")
    }
}

dependencies {

    compileOnly("org.projectlombok:lombok:$lombok")
    annotationProcessor("org.projectlombok:lombok:$lombok")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:$springCloudGateway")
}

tasks.test {
    useJUnitPlatform()
}