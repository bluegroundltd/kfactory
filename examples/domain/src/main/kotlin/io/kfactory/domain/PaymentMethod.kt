package io.kfactory.domain

import io.kfactory.domain.payment.PaymentType

data class PaymentMethod(
  val type: PaymentType,
  val providerName: String
)
