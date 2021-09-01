package io.github.bluegroundltd.factories.booking.traits

import io.github.bluegroundltd.factories.booking.BookingFactory
import io.github.bluegroundltd.kfactory.FactoryTrait
import kotlinx.datetime.Clock
import kotlin.random.Random
import kotlin.time.Duration

object PastBookingTrait : FactoryTrait<BookingFactory> {

  override fun modifyWithTrait(factory: BookingFactory): BookingFactory {
    val now = Clock.System.now()
    val pastCheckInRandomDays = Random.nextInt(7, 14)
    val pastCheckOutRandomDays = pastCheckInRandomDays + Random.nextInt(1, 6)

    return factory
      .withCheckInDate { now.minus(Duration.days(pastCheckInRandomDays)).toString() }
      .withCheckOutDate { now.plus(Duration.days(pastCheckOutRandomDays)).toString() }
  }
}
