package io.kfactory.factories.cost.traits

import io.kfactory.core.FactoryTrait
import io.kfactory.factories.cost.CostFactory

object EuroCurrencyTrait : FactoryTrait<CostFactory> {
  override fun modifyWithTrait(factory: CostFactory): CostFactory =
    factory.withCurrency(currency = "EUR")
}
