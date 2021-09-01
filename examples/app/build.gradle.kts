val kotlinVersion: String by project
val micronautBOMVersion: String by project
val micronautDataBOMVersion: String by project
val micronautJDBCHikariVersion: String by project
val micronautRabbitMQVersion: String by project
val postgreSQLJDBCVersion: String by project

plugins {
  application
  id("com.github.johnrengelman.shadow") version "7.0.0"
  kotlin("jvm")
  kotlin("kapt")
  id("org.jetbrains.kotlin.plugin.noarg")
}

description = "Showcase Application"
group = "com.blueground"
version = "1.0.0"

application {
  mainClass.set("com.blueground.app.App")
}

noArg {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.MappedSuperclass")
  annotation("javax.persistence.Embeddable")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
  mergeServiceFiles()
  manifest {
    attributes["Main-Class"] = "com.blueground.app.App"
  }
}

tasks.build.get().dependsOn(tasks.named("shadowJar"))

val javaVersion = JavaVersion.VERSION_11

java {
  sourceCompatibility = javaVersion
  targetCompatibility = javaVersion
}

kapt {
  arguments {
    arg("micronaut.processing.incremental", true)
  }
}

dependencies {
  implementation(project(":domain"))
  implementation(project(":factories"))
  implementation(project(":kfactory"))

  implementation("com.github.javafaker:javafaker:1.0.2")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")

  implementation("org.postgresql:postgresql:$postgreSQLJDBCVersion")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  implementation("org.mongodb:mongodb-driver-sync:4.0.4")

  // Micronaut Main Deps
  implementation(platform("io.micronaut:micronaut-bom:$micronautBOMVersion"))
  kapt(platform("io.micronaut:micronaut-bom:$micronautBOMVersion"))

  implementation("io.micronaut:micronaut-http-server-netty")
  implementation("io.micronaut:micronaut-management")
  implementation("io.micronaut:micronaut-runtime")
  implementation("io.micronaut:micronaut-aop")
  implementation("io.micronaut.kotlin:micronaut-kotlin-runtime:3.0.0")
  implementation("io.micronaut.security:micronaut-security")
  implementation("io.micronaut.mongodb:micronaut-mongo-reactive")
  kapt("io.micronaut:micronaut-inject-java")
  kapt("io.micronaut.security:micronaut-security-annotations")

  // Micronaut Data Deps
  implementation(platform("io.micronaut.data:micronaut-data-bom:$micronautDataBOMVersion"))
  implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
  implementation("io.micronaut.flyway:micronaut-flyway")
  kapt(platform("io.micronaut.data:micronaut-data-bom:$micronautDataBOMVersion"))
  kapt("io.micronaut.data:micronaut-data-processor")

  // Micronaut Configuration Deps
  implementation("io.micronaut.sql:micronaut-jdbc-hikari:$micronautJDBCHikariVersion")

  testImplementation("junit:junit:4.12")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
