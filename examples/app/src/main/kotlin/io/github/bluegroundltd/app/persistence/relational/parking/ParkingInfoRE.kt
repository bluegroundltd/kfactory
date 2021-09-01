package io.github.bluegroundltd.app.persistence.relational.parking

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "parking_info")
class ParkingInfoRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @Column(name = "covered")
  val covered: Boolean,

  @Column(name = "parking_provider")
  val parkingProvider: String
)
