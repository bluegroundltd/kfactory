package io.github.bluegroundltd.app.persistence.relational.property

import io.github.bluegroundltd.app.persistence.relational.REConverter
import io.github.bluegroundltd.app.persistence.relational.address.AddressREConverter
import io.github.bluegroundltd.app.persistence.relational.parking.ParkingInfoREConverter
import io.github.bluegroundltd.domain.Property

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
