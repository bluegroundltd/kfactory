package io.github.bluegroundltd.app.persistence.relational.customer

import io.github.bluegroundltd.app.persistence.relational.REConverter
import io.github.bluegroundltd.domain.Customer

object CustomerREConverter : REConverter<Customer, CustomerRE> {
  override fun toEntity(domain: Customer): CustomerRE = CustomerRE(
    name = domain.name,
    surname = domain.surname,
    dateOfBirth = domain.dateOfBirth,
    email = domain.email
  )

  override fun toDomain(entity: CustomerRE): Customer = Customer(
    name = entity.name,
    surname = entity.surname,
    dateOfBirth = entity.dateOfBirth,
    email = entity.email
  )
}
