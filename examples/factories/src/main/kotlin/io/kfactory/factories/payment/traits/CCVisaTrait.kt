package io.kfactory.factories.payment.traits

import io.kfactory.core.FactoryTrait
import io.kfactory.domain.payment.PaymentType
import io.kfactory.factories.payment.PaymentMethodFactory

object CCVisaTrait : FactoryTrait<PaymentMethodFactory> {
  override fun modifyWithTrait(factory: PaymentMethodFactory): PaymentMethodFactory = factory
    .withType(paymentType = PaymentType.CREDIT_CARD)
    .withProviderName(providerName = "VISA")
}
