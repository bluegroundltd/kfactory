package io.github.bluegroundltd.domain

import io.github.bluegroundltd.domain.payment.PaymentType

data class PaymentMethod(
  val type: PaymentType,
  val providerName: String
)
