package io.github.bluegroundltd.app.persistence.relational.address

import io.github.bluegroundltd.app.persistence.relational.REConverter
import io.github.bluegroundltd.domain.Address

object AddressREConverter : REConverter<Address, AddressRE> {
  override fun toEntity(domain: Address): AddressRE = AddressRE(
    city = domain.city,
    state = domain.state,
    country = domain.country
  )

  override fun toDomain(entity: AddressRE): Address = Address(
    city = entity.city,
    state = entity.state,
    country = entity.country
  )
}
