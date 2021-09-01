package io.github.bluegroundltd.factories.address.traits

import com.github.javafaker.Faker
import io.github.bluegroundltd.factories.address.AddressFactory
import io.github.bluegroundltd.kfactory.FactoryTrait

object JapanTrait : FactoryTrait<AddressFactory> {
  override fun modifyWithTrait(factory: AddressFactory): AddressFactory = factory
    .withCity { Faker().address().city() }
    .withState { Faker().address().state() }
    .withCountry(country = "Japan")
}
