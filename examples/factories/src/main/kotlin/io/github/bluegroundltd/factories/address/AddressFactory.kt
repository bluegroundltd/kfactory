package io.github.bluegroundltd.factories.address

import com.github.javafaker.Faker
import io.github.bluegroundltd.domain.Address
import io.github.bluegroundltd.kfactory.Factory
import io.github.bluegroundltd.kfactory.TraitEnhancedFactory
import io.github.bluegroundltd.kfactory.Yielded

open class AddressFactory(
  private var city: Yielded<String> = { Faker().address().city() },
  private var state: Yielded<String> = { Faker().address().state() },
  private var country: Yielded<String> = { Faker().address().country() }
) : Factory<Address>, TraitEnhancedFactory {

  companion object Default : AddressFactory()

  fun withCity(city: String) = apply {
    this.city = { city }
  }

  fun withCity(city: Yielded<String>) = apply {
    this.city = city
  }

  fun withState(state: String) = apply {
    this.state = { state }
  }

  fun withState(state: Yielded<String>) = apply {
    this.state = state
  }

  fun withCountry(country: String) = apply {
    this.country = { country }
  }

  fun withCountry(country: Yielded<String>) = apply {
    this.country = country
  }

  override fun produce(): Address = Address(
    city = city(),
    state = state(),
    country = country()
  )
}
