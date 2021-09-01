package io.github.bluegroundltd.factories.payment.traits

import io.github.bluegroundltd.domain.payment.PaymentType
import io.github.bluegroundltd.factories.payment.PaymentMethodFactory
import io.github.bluegroundltd.kfactory.FactoryTrait

object PaypalTrait : FactoryTrait<PaymentMethodFactory> {
  override fun modifyWithTrait(factory: PaymentMethodFactory): PaymentMethodFactory = factory
    .withType(paymentType = PaymentType.PAYPAL)
    .withProviderName(providerName = "PayPal")
}
