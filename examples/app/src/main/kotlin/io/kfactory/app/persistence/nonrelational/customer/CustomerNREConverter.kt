package io.kfactory.app.persistence.nonrelational.customer

import io.kfactory.app.persistence.nonrelational.NREConverter
import io.kfactory.domain.Customer

object CustomerNREConverter : NREConverter<Customer, CustomerNRE> {
  override fun toEntity(domain: Customer): CustomerNRE = CustomerNRE(
    name = domain.name,
    surname = domain.surname,
    dateOfBirth = domain.dateOfBirth,
    email = domain.email
  )

  override fun toDomain(entity: CustomerNRE): Customer = Customer(
    name = entity.name,
    surname = entity.surname,
    dateOfBirth = entity.dateOfBirth,
    email = entity.email
  )
}
