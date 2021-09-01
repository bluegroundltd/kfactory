package io.kfactory.app.persistence.relational.property

import io.kfactory.app.persistence.relational.REConverter
import io.kfactory.app.persistence.relational.address.AddressREConverter
import io.kfactory.app.persistence.relational.parking.ParkingInfoREConverter
import io.kfactory.domain.Property

object PropertyREConverter : REConverter<Property, PropertyRE> {
  override fun toEntity(domain: Property): PropertyRE = PropertyRE(
    size = domain.size,
    numOfBedrooms = domain.numOfBedrooms,
    numOfWC = domain.numOfWC,
    floor = domain.floor,
    furnished = domain.isFurnished,
    address = AddressREConverter.toEntity(domain = domain.address),
    parkingInfo = ParkingInfoREConverter.toEntity(domain = domain.parkingInfo)
  )

  override fun toDomain(entity: PropertyRE): Property = Property(
    size = entity.size,
    numOfBedrooms = entity.numOfBedrooms,
    numOfWC = entity.numOfWC,
    floor = entity.floor,
    isFurnished = entity.furnished,
    address = AddressREConverter.toDomain(entity = entity.address),
    parkingInfo = ParkingInfoREConverter.toDomain(entity = entity.parkingInfo)
  )
}
