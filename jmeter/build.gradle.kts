plugins {
    application
}

group = "ru.a2n.sfm"

val apacheJMeter: String by rootProject.extra


application {
    mainClass = "ru.a2n.sfm.person.Main"
}

dependencies {
    implementation("org.apache.jmeter:ApacheJMeter_core:${apacheJMeter}")
    implementation("org.apache.jmeter:ApacheJMeter_http:${apacheJMeter}")
}
