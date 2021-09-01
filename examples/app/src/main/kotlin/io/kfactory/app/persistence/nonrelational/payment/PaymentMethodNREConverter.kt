package io.kfactory.app.persistence.nonrelational.payment

import io.kfactory.app.persistence.nonrelational.NREConverter
import io.kfactory.domain.PaymentMethod

object PaymentMethodNREConverter : NREConverter<PaymentMethod, PaymentMethodNRE> {
  override fun toEntity(domain: PaymentMethod): PaymentMethodNRE = PaymentMethodNRE(
    type = domain.type,
    providerName = domain.providerName
  )

  override fun toDomain(entity: PaymentMethodNRE): PaymentMethod = PaymentMethod(
    type = entity.type,
    providerName = entity.providerName
  )
}
