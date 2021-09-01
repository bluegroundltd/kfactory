package io.kfactory.domain

data class Property(
  val address: Address,
  val size: Int,
  val numOfBedrooms: Int,
  val numOfWC: Int,
  val floor: Int,
  val parkingInfo: ParkingInfo,
  val isFurnished: Boolean
)
