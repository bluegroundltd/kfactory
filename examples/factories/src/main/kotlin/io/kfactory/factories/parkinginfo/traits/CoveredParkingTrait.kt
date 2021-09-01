package io.kfactory.factories.parkinginfo.traits

import io.kfactory.core.FactoryTrait
import io.kfactory.factories.parkinginfo.ParkingInfoFactory

object CoveredParkingTrait : FactoryTrait<ParkingInfoFactory> {
  override fun modifyWithTrait(factory: ParkingInfoFactory): ParkingInfoFactory =
    factory.withCovered(covered = true)
}
