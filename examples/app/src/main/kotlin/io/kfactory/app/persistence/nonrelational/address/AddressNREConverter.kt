package io.kfactory.app.persistence.nonrelational.address

import io.kfactory.app.persistence.nonrelational.NREConverter
import io.kfactory.domain.Address

object AddressNREConverter : NREConverter<Address, AddressNRE> {
  override fun toEntity(domain: Address): AddressNRE = AddressNRE(
    city = domain.city,
    state = domain.state,
    country = domain.country
  )

  override fun toDomain(entity: AddressNRE): Address = Address(
    city = entity.city,
    state = entity.state,
    country = entity.country
  )
}
