plugins {
  kotlin("jvm")
}

dependencies {
  implementation(project(":domain"))
  implementation(project(":core"))
  implementation(kotlin("stdlib"))
  implementation("com.github.javafaker:javafaker:1.0.2")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
}
