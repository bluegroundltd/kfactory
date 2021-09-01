package io.github.bluegroundltd.factories.property.traits

import io.github.bluegroundltd.factories.property.PropertyFactory
import io.github.bluegroundltd.kfactory.FactoryTrait
import kotlin.random.Random

enum class ApartmentSizeTrait : FactoryTrait<PropertyFactory> {
  SMALL {
    override fun modifyWithTrait(factory: PropertyFactory): PropertyFactory = factory
      .withSize(size = Random.nextInt(30, 60))
      .withNumOfBedrooms(numOfBedrooms = 1)
      .withNumOfWC(numOfWC = 1)
  },
  MEDIUM {
    override fun modifyWithTrait(factory: PropertyFactory): PropertyFactory = factory
      .withSize(size = Random.nextInt(60, 90))
      .withNumOfBedrooms(numOfBedrooms = 2)
      .withNumOfWC(numOfWC = 1)
  },
  LARGE {
    override fun modifyWithTrait(factory: PropertyFactory): PropertyFactory = factory
      .withSize(size = Random.nextInt(90, 150))
      .withNumOfBedrooms(numOfBedrooms = 4)
      .withNumOfWC(numOfWC = 2)
  }
}
