package io.github.bluegroundltd.app.persistence.nonrelational.cost

import io.github.bluegroundltd.app.persistence.nonrelational.NonRelationalEntity
import org.bson.types.ObjectId

@NonRelationalEntity
data class CostNRE(
  val id: ObjectId? = null,
  var amount: Int,
  var currency: String
) {
  constructor() : this(null, -1, "")
}
