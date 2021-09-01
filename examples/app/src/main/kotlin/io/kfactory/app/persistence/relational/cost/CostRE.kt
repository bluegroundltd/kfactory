package io.kfactory.app.persistence.relational.cost

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cost")
class CostRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @Column(name = "cost")
  val amount: Int,

  @Column(name = "currency")
  val currency: String
)
