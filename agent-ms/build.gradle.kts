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
}

dependencies {
    implementation(project(":message-lib"))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.5.4")

    annotationProcessor("org.mapstruct:mapstruct-processor")
    implementation("org.mapstruct:mapstruct:1.6.0")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.testcontainers:junit-jupiter")
//    testImplementation("org.junit.jupiter:junit-jupiter-api")
//    testImplementation("org.junit.jupiter:junit-jupiter-engine")
//    testImplementation("org.springframework:spring-test")
//    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.mockito:mockito-core")

    runtimeOnly("com.h2database:h2")
}