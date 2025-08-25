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

val springdoc: String by project.properties
val springCloudGateway: String by rootProject.extra
val springBootDependencies: String by rootProject.extra
//dependencyManagement {
//    imports {
//        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springBootDependencies")
//    }
//}

dependencies {
    implementation(project(":message-lib"))
    implementation(project(":appointment-lib"))
    implementation(project(":common-lib"))

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor")

    compileOnly("org.projectlombok:lombok")

    implementation("org.liquibase:liquibase-core")
    implementation("org.mapstruct:mapstruct")
    implementation("org.postgresql:postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}