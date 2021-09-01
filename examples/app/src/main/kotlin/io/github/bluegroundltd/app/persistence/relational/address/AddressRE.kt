package io.github.bluegroundltd.app.persistence.relational.address

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "address")
class AddressRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @Column(name = "city")
  val city: String,

  @Column(name = "state")
  val state: String,

  @Column(name = "country")
  val country: String
) {
  override fun toString(): String = "AddressRE(id=$id, city='$city', state='$state', country='$country')"
}

fun AddressRE.isSameAddress(address: String): Boolean {
  val parts = address.split("-")

  if (parts.size < 3) return false

  return parts[0] == city && parts[1] == state && parts[2] == country
}
