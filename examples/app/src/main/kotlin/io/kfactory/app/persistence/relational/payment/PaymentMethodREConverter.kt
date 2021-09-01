package io.kfactory.app.persistence.relational.payment

import io.kfactory.app.persistence.relational.REConverter
import io.kfactory.domain.PaymentMethod

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
