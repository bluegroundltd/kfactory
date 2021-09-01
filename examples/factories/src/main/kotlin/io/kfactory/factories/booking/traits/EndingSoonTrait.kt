package io.kfactory.factories.booking.traits

import com.github.javafaker.Faker
import io.kfactory.core.FactoryTrait
import io.kfactory.factories.booking.BookingFactory
import java.util.concurrent.TimeUnit

object EndingSoonTrait : FactoryTrait<BookingFactory> {
  override fun modifyWithTrait(factory: BookingFactory): BookingFactory = factory
    .withCheckOutDate { Faker().date().future(7, TimeUnit.DAYS).toString() }
}
