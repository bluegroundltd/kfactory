package io.github.bluegroundltd.factories.payment

import com.github.javafaker.Faker
import io.github.bluegroundltd.domain.PaymentMethod
import io.github.bluegroundltd.domain.payment.PaymentType
import io.github.bluegroundltd.kfactory.Factory
import io.github.bluegroundltd.kfactory.TraitEnhancedFactory
import io.github.bluegroundltd.kfactory.Yielded

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
