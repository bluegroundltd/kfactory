package io.github.bluegroundltd.app.persistence.nonrelational.customer

import io.github.bluegroundltd.app.persistence.nonrelational.NREConverter
import io.github.bluegroundltd.domain.Customer

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
