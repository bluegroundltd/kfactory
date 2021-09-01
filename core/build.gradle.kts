plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka") version "1.5.31"
}

repositories {
  mavenCentral()
}

tasks.dokkaHtml.configure {
  outputDirectory.set(buildDir.resolve("dokka-html"))
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
}
