package io.github.bluegroundltd.app.persistence.nonrelational.booking

import io.github.bluegroundltd.app.persistence.nonrelational.NonRelationalEntity
import io.github.bluegroundltd.app.persistence.nonrelational.cost.CostNRE
import io.github.bluegroundltd.app.persistence.nonrelational.customer.CustomerNRE
import io.github.bluegroundltd.app.persistence.nonrelational.payment.PaymentMethodNRE
import io.github.bluegroundltd.app.persistence.nonrelational.property.PropertyNRE
import org.bson.types.ObjectId
import java.util.*

@NonRelationalEntity
data class BookingNRE(
  val id: ObjectId? = null,
  var property: PropertyNRE,
  var checkInDate: String,
  var checkOutDate: String,
  var paymentMethod: PaymentMethodNRE,
  var customer: CustomerNRE,
  var cost: CostNRE
) {
  constructor() : this(null, PropertyNRE(), "", "", PaymentMethodNRE(), CustomerNRE(), CostNRE())
}
