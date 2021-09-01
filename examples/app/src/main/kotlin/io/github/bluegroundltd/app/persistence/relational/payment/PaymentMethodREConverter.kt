package io.github.bluegroundltd.app.persistence.relational.payment

import io.github.bluegroundltd.app.persistence.relational.REConverter
import io.github.bluegroundltd.domain.PaymentMethod

object PaymentMethodREConverter : REConverter<PaymentMethod, PaymentMethodRE> {
  override fun toEntity(domain: PaymentMethod): PaymentMethodRE = PaymentMethodRE(
    type = domain.type,
    providerName = domain.providerName
  )

  override fun toDomain(entity: PaymentMethodRE): PaymentMethod = PaymentMethod(
    type = entity.type,
    providerName = entity.providerName
  )
}
