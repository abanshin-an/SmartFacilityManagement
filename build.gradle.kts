import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import name.remal.gradle_plugins.sonarlint.SonarLint
import name.remal.gradle_plugins.sonarlint.SonarLintExtension

plugins {
    id("io.spring.dependency-management")
    id("name.remal.sonarlint") apply false
    id("com.diffplug.spotless") apply false
    id("org.springframework.boot") apply false
}

val mapstruct: String by project.properties
val junit: String by project.properties

allprojects {
    group = "ru.otus.javaadvanced"

    repositories {
        mavenLocal()
        mavenCentral()
    }

    val caffeine: String by rootProject.extra
    val expressly: String by rootProject.extra
    val guava: String by rootProject.extra
    val h2database: String by rootProject.extra
    val hibernateValidator: String by rootProject.extra
    val jakartaEl: String by rootProject.extra
    val junit: String by rootProject.extra
    val liquibaseCore: String by rootProject.extra
    val micrometer: String by rootProject.extra
    val postgres: String by rootProject.extra
    val resilience4j: String by rootProject.extra
    val springBootCloud: String by rootProject.extra
    val springdoc: String by rootProject.extra
    val testcontainers: String by rootProject.extra
    val springframework: String by rootProject.extra
    val lombok: String by rootProject.extra

    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springBootCloud")
                mavenBom("org.junit:junit-bom:${junit}")
                mavenBom("org.testcontainers:testcontainers-bom:${testcontainers}")
            }
            dependency("com.google.guava:guava:$guava")
        }
    }


    configurations.all {
        resolutionStrategy {
            failOnVersionConflict()

            force("com.google.errorprone:error_prone_annotations:2.27.0")
            force("commons-io:commons-io:2.16.1")
            force("commons-logging:commons-logging:1.3.0")
            force("io.swagger.core.v3:swagger-annotations:2.2.23")
            force("org.apache.commons:commons-compress:1.26.1")
            force("org.eclipse.jgit:org.eclipse.jgit:6.9.0.202403050737-r")
            force("org.jetbrains:annotations:19.0.0")
            force("org.junit:junit-bom:$junit")

            force("com.formdev:svgSalamander:1.1.4")
            force("com.helger.commons:ph-commons:10.2.5")
            force("io.github.resilience4j:resilience4j-spring-boot3:$resilience4j")
            force("io.micrometer:micrometer-core:$micrometer")
            force("io.micrometer:micrometer-observation:$micrometer")
            force("io.micrometer:micrometer-registry-prometheus:$micrometer")
            force("net.minidev:accessors-smart:2.5.2")
            force("org.apache.commons:commons-text:1.13.0")
            force("org.apache.httpcomponents:httpclient:4.5.14")
            force("org.apache.xmlgraphics:xmlgraphics-commons:2.9")
            force("org.checkerframework:checker-qual:3.42.0")
            force("org.glassfish.expressly:expressly:$expressly")
            force("org.glassfish:jakarta.el:$jakartaEl")
            force("org.hibernate.validator:hibernate-validator:$hibernateValidator")
            force("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.8.1")
            force("org.liquibase:liquibase-core:$liquibaseCore")
            force("org.mapstruct:mapstruct-processor:$mapstruct")
            force("org.mapstruct:mapstruct:$mapstruct")
            force("org.ow2.asm:asm:9.7.1")
            force("org.postgresql:postgresql:$postgres")
            force("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")
            force("com.github.ben-manes.caffeine:caffeine:$caffeine")
            force("com.h2database:h2:$h2database")
            force("org.springframework:spring-web:$springframework")
            force("org.springframework:spring-beans:$springframework")
            force("org.springframework:spring-core between:6.2.7")
            force("org.springframework:spring-context:6.2.7")
            force("org.springframework:spring-aop:6.2.7")
            force("org.springframework:spring-expression:6.2.9")
            force("org.slf4j:slf4j-api:2.0.17")
            force("org.yaml:snakeyaml:2.4")
            force("org.springframework.boot:spring-boot:3.5.4")
            force("org.springframework.boot:spring-boot-autoconfigure:3.5.0")
            force("org.springframework:spring-core:6.2.9")
            force("org.lombok:lombok:1.18.38")
            force("commons-codec:commons-codec:1.16.1")
            force("org.apache.commons:commons-lang3:3.14.0")
            force("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
            force("com.google.code.gson:gson:2.11.0")
            force("org.jetbrains.kotlin:kotlin-stdlib-common:1.6.10")


        }
    }
}

subprojects {
    plugins.apply(JavaPlugin::class.java)
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing"))
    }

    apply<name.remal.gradle_plugins.sonarlint.SonarLintPlugin>()
    configure<SonarLintExtension> {
        nodeJs {
            detectNodeJs.set(false)
            logNodeJsNotFound.set(false)
        }
    }

    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            palantirJavaFormat("2.39.0")
        }
    }

    tasks.withType<SonarLint> {
        dependsOn("spotlessApply")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging.showExceptions = true
        setJvmArgs(listOf("-XX:+StartAttachListener"))
        reports {
            junitXml.required.set(true)
            html.required.set(true)
        }
    }
}

tasks {
    val managedVersions by registering {
        doLast {
            project.extensions.getByType<DependencyManagementExtension>()
                .managedVersions
                .toSortedMap()
                .map { "${it.key}:${it.value}" }
                .forEach(::println)
        }
    }
}