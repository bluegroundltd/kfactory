package io.kfactory.app.persistence.relational.parking

import io.kfactory.app.persistence.relational.REConverter
import io.kfactory.domain.ParkingInfo

object ParkingInfoREConverter : REConverter<ParkingInfo, ParkingInfoRE> {
  override fun toEntity(domain: ParkingInfo): ParkingInfoRE = ParkingInfoRE(
    covered = domain.covered,
    parkingProvider = domain.parkingProvider
  )

  override fun toDomain(entity: ParkingInfoRE): ParkingInfo = ParkingInfo(
    covered = entity.covered,
    parkingProvider = entity.parkingProvider
  )
}
