package io.github.bluegroundltd.factories.parkinginfo.traits

import io.github.bluegroundltd.factories.parkinginfo.ParkingInfoFactory
import io.github.bluegroundltd.kfactory.FactoryTrait

object CoveredParkingTrait : FactoryTrait<ParkingInfoFactory> {
  override fun modifyWithTrait(factory: ParkingInfoFactory): ParkingInfoFactory =
    factory.withCovered(covered = true)
}
