plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management") version "1.1.7"
    id("me.champeau.jmh") version "0.7.3"
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
}

val springdoc: String by project.properties
val springCloudGateway: String by rootProject.extra

dependencies {
    implementation(project(":person-lib"))

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")
    implementation("org.springframework.security:spring-security-crypto")

    annotationProcessor("org.mapstruct:mapstruct-processor")
    implementation("org.mapstruct:mapstruct")
    implementation("org.postgresql:postgresql")

    implementation("org.liquibase:liquibase-core")

    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    runtimeOnly("org.postgresql:postgresql")
}

jmh {
    zip64 = true
    includes.addAll("ru.a2n.sfm.person.jmh")
}