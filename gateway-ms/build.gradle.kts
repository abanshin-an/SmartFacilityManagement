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

val resilience4j: String by project.properties
val springdoc: String by project.properties
val springBootCloud: String by project.properties
val springCloudGateway: String by project.properties
val springCloudCircuitbreaker: String by project.properties
val apectjweaver: String by project.properties
val awaitility: String by project.properties


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux:$springCloudGateway")
    implementation("org.springframework.cloud:spring-cloud-dependencies:$springBootCloud")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-observation")
    implementation("org.aspectj:aspectjweaver:$apectjweaver")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.github.resilience4j:resilience4j-spring-boot3:$resilience4j")
    implementation("io.github.resilience4j:resilience4j-reactor:$resilience4j")
    implementation("io.github.resilience4j:resilience4j-circuitbreaker:$resilience4j")
    implementation("io.github.resilience4j:resilience4j-timelimiter:$resilience4j")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j:$springCloudCircuitbreaker")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:$springCloudGateway")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("io.projectreactor:reactor-test")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.awaitility:awaitility:$awaitility")
}