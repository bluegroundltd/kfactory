package io.kfactory.factories.cost

import com.github.javafaker.Faker
import io.kfactory.core.Factory
import io.kfactory.core.TraitEnhancedFactory
import io.kfactory.core.Yielded
import io.kfactory.domain.Cost

open class CostFactory(
  private var amount: Yielded<Int> = { Faker().random().nextInt(500, 2000) },
  private var currency: Yielded<String> = { Faker().currency().name() }
) : Factory<Cost>, TraitEnhancedFactory {

  companion object Default : CostFactory()

  fun withAmount(amount: Int) = apply {
    this.amount = { amount }
  }

  fun withAmount(amount: Yielded<Int>) = apply {
    this.amount = amount
  }

  fun withCurrency(currency: String) = apply {
    this.currency = { currency }
  }

  fun withCurrency(currency: Yielded<String>) = apply {
    this.currency = currency
  }

  override fun produce(): Cost = Cost(
    amount = amount(),
    currency = currency(),
  )
}
