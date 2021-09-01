package io.kfactory.factories.property

import io.kfactory.core.Factory
import io.kfactory.core.TraitEnhancedFactory
import io.kfactory.core.Yielded
import io.kfactory.domain.Address
import io.kfactory.domain.ParkingInfo
import io.kfactory.domain.Property
import io.kfactory.factories.address.AddressFactory
import io.kfactory.factories.parkinginfo.ParkingInfoFactory
import kotlin.random.Random

open class PropertyFactory(
  private var address: Yielded<Address> = { AddressFactory.produce() },
  private var size: Yielded<Int> = { Random.nextInt(0, 300) },
  private var numOfBedrooms: Yielded<Int> = { Random.nextInt(1, 4) },
  private var numOfWC: Yielded<Int> = { Random.nextInt(1, 3) },
  private var floor: Yielded<Int> = { Random.nextInt(1, 6) },
  private var parkingInfo: Yielded<ParkingInfo> = { ParkingInfoFactory.produce() },
  private var isFurnished: Yielded<Boolean> = { Random.nextBoolean() }
) : Factory<Property>, TraitEnhancedFactory {

  companion object Default : PropertyFactory()

  fun withAddress(address: Address) = apply {
    this.address = { address }
  }

  fun withAddress(address: Yielded<Address>) = apply {
    this.address = address
  }

  fun withSize(size: Int) = apply {
    this.size = { size }
  }

  fun withSize(size: Yielded<Int>) = apply {
    this.size = size
  }

  fun withNumOfBedrooms(numOfBedrooms: Int) = apply {
    this.numOfBedrooms = { numOfBedrooms }
  }

  fun withNumOfBedrooms(numOfBedrooms: Yielded<Int>) = apply {
    this.numOfBedrooms = numOfBedrooms
  }

  fun withNumOfWC(numOfWC: Int) = apply {
    this.numOfWC = { numOfWC }
  }

  fun withNumOfWC(numOfWC: Yielded<Int>) = apply {
    this.numOfWC = numOfWC
  }

  fun withFloor(floor: Int) = apply {
    this.floor = { floor }
  }

  fun withFloor(floor: Yielded<Int>) = apply {
    this.floor = floor
  }

  fun withParkingInfo(parkingInfo: ParkingInfo) = apply {
    this.parkingInfo = { parkingInfo }
  }

  fun withParkingInfo(parkingInfo: Yielded<ParkingInfo>) = apply {
    this.parkingInfo = parkingInfo
  }

  fun withFurnished(isFurnished: Boolean) = apply {
    this.isFurnished = { isFurnished }
  }

  fun withFurnished(isFurnished: Yielded<Boolean>) = apply {
    this.isFurnished = isFurnished
  }

  override fun produce(): Property = Property(
    address = address(),
    size = size(),
    numOfBedrooms = numOfBedrooms(),
    numOfWC = numOfWC(),
    floor = floor(),
    parkingInfo = parkingInfo(),
    isFurnished = isFurnished()
  )
}
