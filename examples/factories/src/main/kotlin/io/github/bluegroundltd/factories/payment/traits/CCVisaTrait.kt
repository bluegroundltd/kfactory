package io.github.bluegroundltd.factories.payment.traits

import io.github.bluegroundltd.domain.payment.PaymentType
import io.github.bluegroundltd.factories.payment.PaymentMethodFactory
import io.github.bluegroundltd.kfactory.FactoryTrait

object CCVisaTrait : FactoryTrait<PaymentMethodFactory> {
  override fun modifyWithTrait(factory: PaymentMethodFactory): PaymentMethodFactory = factory
    .withType(paymentType = PaymentType.CREDIT_CARD)
    .withProviderName(providerName = "VISA")
}
