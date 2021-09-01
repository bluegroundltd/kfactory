pluginManagement {
  resolutionStrategy {
    eachPlugin {
      val detektVersion: String by settings
      val kotlinVersion: String by settings
      val spotlessVersion: String by settings

      when {
        requested.id.namespace?.startsWith("org.jetbrains.kotlin") == true -> useVersion(kotlinVersion)
        requested.id.namespace?.startsWith("org.jetbrains.kotlin.plugin.noarg") == true -> useVersion(kotlinVersion)
        requested.id.id == "io.gitlab.arturbosch.detekt" -> useVersion(detektVersion)
        requested.id.id == "com.diffplug.gradle.spotless" -> useVersion(spotlessVersion)
      }
    }
  }
}

rootProject.name = "kfactory"

include(
  "app",
  "domain",
  "factories",
  "core"
)

project(":app").projectDir = file("examples/app")
project(":domain").projectDir = file("examples/domain")
project(":factories").projectDir = file("examples/factories")
