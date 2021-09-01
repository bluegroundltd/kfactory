import com.diffplug.gradle.spotless.SpotlessExtension

val detektVersion: String by project

plugins {
  groovy
  codenarc
  kotlin("jvm")
  kotlin("kapt")
  id("io.gitlab.arturbosch.detekt")
  id("com.diffplug.gradle.spotless")
}

allprojects {
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "groovy")
  apply(plugin = "codenarc")
}

configure(subprojects) {
  apply(plugin = "io.gitlab.arturbosch.detekt")
  apply(plugin = "com.diffplug.gradle.spotless")

  val subprojectJvmTarget = JavaVersion.VERSION_11.toString()

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("spotlessKotlinApply")

    sourceCompatibility = subprojectJvmTarget
    targetCompatibility = subprojectJvmTarget

    kotlinOptions {
      jvmTarget = subprojectJvmTarget
      freeCompilerArgs = listOf("-Xjsr305=strict", "-Xopt-in=kotlin.time.ExperimentalTime")
      languageVersion = "1.5"
    }
  }

  detekt {
    parallel = true
    buildUponDefaultConfig = true
    toolVersion = detektVersion
    config = files(rootProject.file("config/detekt/detekt.yml"))
    baseline = file(rootProject.file("config/detekt/baseline.xml"))

    reports {
      xml.enabled = false
      txt.enabled = false
      html.destination = file("$buildDir/reports/detekt/detekt.html")
    }
  }

  tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    jvmTarget = subprojectJvmTarget
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    exclude("**/test/**")
  }

  configure<SpotlessExtension> {
    val ktLintIndentationData = mapOf(
      "indent_size" to "2",
      "continuation_indent_size" to "4",
      "disabled_rules" to "no-wildcard-imports"
    )

    val ktLintVersion = "0.42.1"

    kotlin {
      target("src/**/*.kt")
      ktlint(ktLintVersion).userData(ktLintIndentationData)
      trimTrailingWhitespace()
      endWithNewline()
    }

    kotlinGradle {
      ktlint(ktLintVersion).userData(ktLintIndentationData)
      trimTrailingWhitespace()
      endWithNewline()
    }
  }
}
