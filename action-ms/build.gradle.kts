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
val resilience4j: String by rootProject.extra

dependencies {
    implementation(project(":message-lib"))
    implementation(project(":appointment-lib"))
    implementation(project(":common-lib"))

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor")

    compileOnly("org.projectlombok:lombok")

    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation("io.prometheus:prometheus-metrics-core")
    implementation("io.github.openfeign:feign-micrometer")
    implementation("io.github.resilience4j:resilience4j-spring-boot3:2.3.0")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.glassfish.expressly:expressly")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.liquibase:liquibase-core")
    implementation("org.mapstruct:mapstruct")
    implementation("org.postgresql:postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.security:spring-security-crypto")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j") {
            exclude(group = "io.github.resilience4j")
    }
    implementation("io.github.resilience4j:resilience4j-spring-boot3:$resilience4j")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.awaitility:awaitility:4.2.0")
    testImplementation("io.projectreactor:reactor-test")
}