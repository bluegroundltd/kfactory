package io.kfactory.factories.payment

import com.github.javafaker.Faker
import io.kfactory.core.Factory
import io.kfactory.core.TraitEnhancedFactory
import io.kfactory.core.Yielded
import io.kfactory.domain.PaymentMethod
import io.kfactory.domain.payment.PaymentType

open class PaymentMethodFactory(
  private var paymentType: Yielded<PaymentType> = { PaymentType.randomPaymentType },
  private var providerName: Yielded<String> = { Faker().finance().creditCard() }
) : Factory<PaymentMethod>, TraitEnhancedFactory {

  companion object Default : PaymentMethodFactory()

  fun withType(paymentType: PaymentType) = apply {
    this.paymentType = { paymentType }
  }

  fun withType(paymentType: Yielded<PaymentType>) = apply {
    this.paymentType = paymentType
  }

  fun withProviderName(providerName: String) = apply {
    this.providerName = { providerName }
  }

  fun withProviderName(providerName: Yielded<String>) = apply {
    this.providerName = providerName
  }

  override fun produce(): PaymentMethod = PaymentMethod(
    type = paymentType(),
    providerName = providerName()
  )
}
