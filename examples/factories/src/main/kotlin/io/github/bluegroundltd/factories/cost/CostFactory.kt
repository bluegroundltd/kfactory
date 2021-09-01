package io.github.bluegroundltd.factories.cost

import com.github.javafaker.Faker
import io.github.bluegroundltd.domain.Cost
import io.github.bluegroundltd.kfactory.Factory
import io.github.bluegroundltd.kfactory.TraitEnhancedFactory
import io.github.bluegroundltd.kfactory.Yielded

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
