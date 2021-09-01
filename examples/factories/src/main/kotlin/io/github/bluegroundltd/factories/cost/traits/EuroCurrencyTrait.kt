package io.github.bluegroundltd.factories.cost.traits

import io.github.bluegroundltd.factories.cost.CostFactory
import io.github.bluegroundltd.kfactory.FactoryTrait

object EuroCurrencyTrait : FactoryTrait<CostFactory> {
  override fun modifyWithTrait(factory: CostFactory): CostFactory =
    factory.withCurrency(currency = "EUR")
}
