package io.kfactory.factories.parkinginfo

import com.github.javafaker.Faker
import io.kfactory.core.Factory
import io.kfactory.core.TraitEnhancedFactory
import io.kfactory.core.Yielded
import io.kfactory.domain.ParkingInfo
import kotlin.random.Random

open class ParkingInfoFactory(
  private var covered: Yielded<Boolean> = { Random.nextBoolean() },
  private var parkingProvider: Yielded<String> = { Faker().company().name() }
) : Factory<ParkingInfo>, TraitEnhancedFactory {

  companion object Default : ParkingInfoFactory()

  fun withCovered(covered: Boolean) = apply {
    this.covered = { covered }
  }

  fun withCovered(covered: Yielded<Boolean>) = apply {
    this.covered = covered
  }

  fun withParking(parkingProvider: String) = apply {
    this.parkingProvider = { parkingProvider }
  }

  fun withParking(parkingProvider: Yielded<String>) = apply {
    this.parkingProvider = parkingProvider
  }

  override fun produce(): ParkingInfo = ParkingInfo(
    covered = covered(),
    parkingProvider = parkingProvider()
  )
}
