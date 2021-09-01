package io.github.bluegroundltd.app.persistence.nonrelational.property

import io.github.bluegroundltd.app.persistence.nonrelational.NonRelationalEntity
import io.github.bluegroundltd.app.persistence.nonrelational.address.AddressNRE
import io.github.bluegroundltd.app.persistence.nonrelational.parking.ParkingInfoNRE
import org.bson.types.ObjectId

@NonRelationalEntity
data class PropertyNRE(
  @javax.persistence.Id
  val id: ObjectId? = null,
  var size: Int,
  var address: AddressNRE,
  var numOfBedrooms: Int,
  var numOfWC: Int,
  var floor: Int,
  var parkingInfo: ParkingInfoNRE,
  var furnished: Boolean
) {
  constructor() : this(null, 0, AddressNRE(), 0, 0, 0, ParkingInfoNRE(), false)
}
