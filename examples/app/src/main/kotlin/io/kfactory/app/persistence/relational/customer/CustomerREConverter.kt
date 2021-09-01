package io.kfactory.app.persistence.relational.customer

import io.kfactory.app.persistence.relational.REConverter
import io.kfactory.domain.Customer

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
