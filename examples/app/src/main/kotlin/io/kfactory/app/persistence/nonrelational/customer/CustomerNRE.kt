package io.kfactory.app.persistence.nonrelational.customer

import io.kfactory.app.persistence.nonrelational.NonRelationalEntity
import org.bson.types.ObjectId
import java.util.*

@NonRelationalEntity
data class CustomerNRE(
  val id: ObjectId? = null,
  var name: String,
  var surname: String,
  var dateOfBirth: String,
  var email: String
) {
  constructor() : this(null, "", "", "", "")
}
