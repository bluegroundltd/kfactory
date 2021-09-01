package io.github.bluegroundltd.app.persistence.nonrelational.payment

import io.github.bluegroundltd.app.persistence.nonrelational.NonRelationalEntity
import io.github.bluegroundltd.domain.payment.PaymentType
import org.bson.types.ObjectId

@NonRelationalEntity
data class PaymentMethodNRE(
  val id: ObjectId? = null,
  var type: PaymentType,
  var providerName: String
) {
  constructor() : this(null, PaymentType.randomPaymentType, "")
}
