package io.github.bluegroundltd.app.persistence.relational.booking

import io.github.bluegroundltd.app.persistence.relational.cost.CostRE
import io.github.bluegroundltd.app.persistence.relational.customer.CustomerRE
import io.github.bluegroundltd.app.persistence.relational.payment.PaymentMethodRE
import io.github.bluegroundltd.app.persistence.relational.property.PropertyRE
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "booking")
class BookingRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @OneToOne(cascade = [CascadeType.ALL], optional = false)
  @JoinColumn(name = "property_id", nullable = false)
  var property: PropertyRE,

  @Column(name = "check_in_date")
  val checkInDate: String,

  @Column(name = "check_out_date")
  val checkOutDate: String,

  @OneToOne(cascade = [CascadeType.ALL], optional = false)
  @JoinColumn(name = "payment_method_id", nullable = false)
  var paymentMethod: PaymentMethodRE,

  @OneToOne(cascade = [CascadeType.ALL], optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  var customer: CustomerRE,

  @OneToOne(cascade = [CascadeType.ALL], optional = false)
  @JoinColumn(name = "cost_id", nullable = false)
  var cost: CostRE
)
