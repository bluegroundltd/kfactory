plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka") version "1.5.31"
  id("com.vanniktech.maven.publish") version "0.18.0"
}

repositories {
  mavenCentral()
}

apply(plugin = "com.vanniktech.maven.publish")

tasks.dokkaHtml.configure {
  outputDirectory.set(buildDir.resolve("dokka-html"))
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

plugins.withId("com.vanniktech.maven.publish") {
  mavenPublish {
    sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
  }
}
