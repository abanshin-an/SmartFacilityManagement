pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    val sonarlint: String by settings
    val spotless: String by settings
    val dependencyManagement: String by settings
    val springBoot: String by settings
    val johnrengelman: String by settings
    val jmh: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagement
        id("name.remal.sonarlint") version sonarlint
        id("com.github.johnrengelman.shadow") version johnrengelman
        id("com.diffplug.spotless") version spotless
        id("org.springframework.boot") version springBoot
        id("org.springframework.boot.aot") version springBoot
        id("me.champeau.jmh") version jmh
    }
}

rootProject.name = "SmartFacilityManagement"
include("action-ms")
include("agent-ms")
include("appointment-lib")
include("appointment-ms")
include("common-lib")
include("facility-lib")
include("facility-ms")
include("gateway-ms")
include("message-lib")
include("person-lib")
include("person-ms")
include("jmeter")
