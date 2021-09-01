package io.kfactory.domain.payment

import kotlin.random.Random

enum class PaymentType(val rawName: String) {
  CREDIT_CARD("credit_card"),
  PAYPAL("paypal");

  companion object {
    val randomPaymentType: PaymentType
      get() = values()[Random.nextInt(0, values().size)]

    fun from(rawName: String): PaymentType = values().first { it.rawName == rawName }
  }
}
