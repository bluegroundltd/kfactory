package io.kfactory.factories.booking.traits

import io.kfactory.core.FactoryTrait
import io.kfactory.factories.booking.BookingFactory
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
