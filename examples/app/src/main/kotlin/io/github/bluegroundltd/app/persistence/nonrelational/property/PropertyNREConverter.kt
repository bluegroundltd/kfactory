package io.github.bluegroundltd.app.persistence.nonrelational.property

import io.github.bluegroundltd.app.persistence.nonrelational.NREConverter
import io.github.bluegroundltd.app.persistence.nonrelational.address.AddressNREConverter
import io.github.bluegroundltd.app.persistence.nonrelational.parking.ParkingInfoNREConverter
import io.github.bluegroundltd.domain.Property

object PropertyNREConverter : NREConverter<Property, PropertyNRE> {
  override fun toEntity(domain: Property): PropertyNRE = PropertyNRE(
    size = domain.size,
    numOfBedrooms = domain.numOfBedrooms,
    numOfWC = domain.numOfWC,
    floor = domain.floor,
    furnished = domain.isFurnished,
    address = AddressNREConverter.toEntity(domain = domain.address),
    parkingInfo = ParkingInfoNREConverter.toEntity(domain = domain.parkingInfo)
  )

  override fun toDomain(entity: PropertyNRE): Property = Property(
    size = entity.size,
    numOfBedrooms = entity.numOfBedrooms,
    numOfWC = entity.numOfWC,
    floor = entity.floor,
    isFurnished = entity.furnished,
    address = AddressNREConverter.toDomain(entity = entity.address),
    parkingInfo = ParkingInfoNREConverter.toDomain(entity = entity.parkingInfo)
  )
}
