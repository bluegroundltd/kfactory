package io.kfactory.factories.payment.traits

import io.kfactory.core.FactoryTrait
import io.kfactory.domain.payment.PaymentType
import io.kfactory.factories.payment.PaymentMethodFactory

object PaypalTrait : FactoryTrait<PaymentMethodFactory> {
  override fun modifyWithTrait(factory: PaymentMethodFactory): PaymentMethodFactory = factory
    .withType(paymentType = PaymentType.PAYPAL)
    .withProviderName(providerName = "PayPal")
}
