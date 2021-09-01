package io.github.bluegroundltd.app.persistence.nonrelational.parking

import io.github.bluegroundltd.app.persistence.nonrelational.NonRelationalEntity
import org.bson.types.ObjectId
import java.util.*

@NonRelationalEntity
data class ParkingInfoNRE(
  val id: ObjectId? = null,
  var covered: Boolean,
  var parkingProvider: String
) {
  constructor() : this(null, false, "")
}
