package io.kfactory.app

import io.micronaut.runtime.Micronaut

object App {

  @JvmStatic
  fun main(args: Array<String>) {
    Micronaut.build()
      .packages("io.kfactory.app")
      .mainClass(App.javaClass)
      .start()
  }
}
