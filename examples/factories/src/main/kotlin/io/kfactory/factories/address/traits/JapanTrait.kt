package io.kfactory.factories.address.traits

import com.github.javafaker.Faker
import io.kfactory.core.FactoryTrait
import io.kfactory.factories.address.AddressFactory

object JapanTrait : FactoryTrait<AddressFactory> {
  override fun modifyWithTrait(factory: AddressFactory): AddressFactory = factory
    .withCity { Faker().address().city() }
    .withState { Faker().address().state() }
    .withCountry(country = "Japan")
}
