package io.kfactory.app.persistence.nonrelational.parking

import io.kfactory.app.persistence.nonrelational.NREConverter
import io.kfactory.domain.ParkingInfo

object ParkingInfoNREConverter : NREConverter<ParkingInfo, ParkingInfoNRE> {
  override fun toEntity(domain: ParkingInfo): ParkingInfoNRE = ParkingInfoNRE(
    covered = domain.covered,
    parkingProvider = domain.parkingProvider
  )

  override fun toDomain(entity: ParkingInfoNRE): ParkingInfo = ParkingInfo(
    covered = entity.covered,
    parkingProvider = entity.parkingProvider
  )
}
