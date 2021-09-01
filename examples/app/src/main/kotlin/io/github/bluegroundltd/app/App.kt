package io.github.bluegroundltd.app

import io.micronaut.runtime.Micronaut

object App {

  @JvmStatic
  fun main(args: Array<String>) {
    Micronaut.build()
      .packages("io.github.bluegroundltd.app")
      .mainClass(App.javaClass)
      .start()
  }
}
