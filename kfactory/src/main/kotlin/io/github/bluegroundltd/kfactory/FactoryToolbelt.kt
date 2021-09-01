package io.github.bluegroundltd.kfactory

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import kotlin.random.Random

/**
 * Interface used for including handy helper functions used by factories
 */
interface FactoryToolbelt {
  /**
   * @return a random UUID in String format
   */
  fun uuid(): String = UUID.randomUUID().toString()

  /**
   * @return now as [LocalDateTime]
   */
  fun dateTime(): LocalDateTime = LocalDateTime.now()

  /**
   * @return now as [LocalDate]
   */
  fun date(): LocalDate = LocalDate.now()

  /**
   * @return now as [LocalTime]
   */
  fun time(): LocalTime = LocalTime.now()

  /**
   * Create a random alphanumeric String of the specified [length]
   * @return the created alphanumeric
   */
  fun randomAlphaNumeric(length: Int): String {
    val charPool: List<Char> = ('A'..'Z') + ('0'..'9')
    return (1..length)
      .map { charPool[Random.nextInt(0, charPool.size)] }
      .joinToString(separator = "")
  }
}
