package io.github.bluegroundltd.app.persistence.relational.payment

import io.github.bluegroundltd.domain.payment.PaymentType
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "payment_method")
class PaymentMethodRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_type")
  val type: PaymentType,

  @Column(name = "provider_name")
  val providerName: String
)
