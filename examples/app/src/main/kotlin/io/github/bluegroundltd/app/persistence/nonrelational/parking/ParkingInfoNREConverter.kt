package io.github.bluegroundltd.app.persistence.nonrelational.parking

import io.github.bluegroundltd.app.persistence.nonrelational.NREConverter
import io.github.bluegroundltd.domain.ParkingInfo

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
