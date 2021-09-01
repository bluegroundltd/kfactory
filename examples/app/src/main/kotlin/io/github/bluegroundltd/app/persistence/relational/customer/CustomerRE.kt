package io.github.bluegroundltd.app.persistence.relational.customer

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customer")
class CustomerRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @Column(name = "name")
  val name: String,

  @Column(name = "surname")
  val surname: String,

  @Column(name = "date_of_birth")
  val dateOfBirth: String,

  @Column(name = "email")
  val email: String
)
