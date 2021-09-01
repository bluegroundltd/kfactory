package io.github.bluegroundltd.factories.booking.traits

import com.github.javafaker.Faker
import io.github.bluegroundltd.factories.booking.BookingFactory
import io.github.bluegroundltd.kfactory.FactoryTrait
import java.util.concurrent.TimeUnit

object EndingSoonTrait : FactoryTrait<BookingFactory> {
  override fun modifyWithTrait(factory: BookingFactory): BookingFactory = factory
    .withCheckOutDate { Faker().date().future(7, TimeUnit.DAYS).toString() }
}
