package io.kfactory.app.persistence.nonrelational.address

import io.kfactory.app.persistence.nonrelational.NonRelationalEntity
import org.bson.types.ObjectId

@NonRelationalEntity
data class AddressNRE(
  val id: ObjectId? = null,
  var city: String,
  var state: String,
  var country: String
) {
  constructor() : this(null, "", "", "")
}

fun AddressNRE.isSameAddress(address: String): Boolean {
  val parts = address.split("-")

  if (parts.size < 3) return false

  return parts[0] == city && parts[1] == state && parts[2] == country
}
