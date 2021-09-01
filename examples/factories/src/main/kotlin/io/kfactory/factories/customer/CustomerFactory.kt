package io.kfactory.factories.customer

import com.github.javafaker.Faker
import io.kfactory.core.Factory
import io.kfactory.core.TraitEnhancedFactory
import io.kfactory.core.Yielded
import io.kfactory.domain.Customer

open class CustomerFactory(
  private var name: Yielded<String> = { Faker().name().firstName() },
  private var surname: Yielded<String> = { Faker().name().lastName() },
  private var dateOfBirth: Yielded<String> = { Faker().date().birthday().toString() },
  private var email: Yielded<String> = { Faker().internet().emailAddress() }
) : Factory<Customer>, TraitEnhancedFactory {

  companion object Default : CustomerFactory()

  fun withName(name: String) = apply {
    this.name = { name }
  }

  fun withName(name: Yielded<String>) = apply {
    this.name = name
  }

  fun withSurname(surname: String) = apply {
    this.surname = { surname }
  }

  fun withSurname(surname: Yielded<String>) = apply {
    this.surname = surname
  }

  fun withDateOfBirth(dateOfBirth: String) = apply {
    this.dateOfBirth = { dateOfBirth }
  }

  fun withDateOfBirth(dateOfBirth: Yielded<String>) = apply {
    this.dateOfBirth = dateOfBirth
  }

  fun withEmail(email: String) = apply {
    this.email = { email }
  }

  fun withEmail(email: Yielded<String>) = apply {
    this.email = email
  }

  override fun produce(): Customer = Customer(
    name = name(),
    surname = surname(),
    dateOfBirth = dateOfBirth(),
    email = email()
  )
}
