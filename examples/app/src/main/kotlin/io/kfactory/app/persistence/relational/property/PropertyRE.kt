package io.kfactory.app.persistence.relational.property

import io.kfactory.app.persistence.relational.address.AddressRE
import io.kfactory.app.persistence.relational.parking.ParkingInfoRE
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "property")
class PropertyRE(
  @Id
  @GeneratedValue
  val id: UUID? = null,

  @Column(name = "size")
  val size: Int,

  @OneToOne(cascade = [CascadeType.ALL], optional = false)
  @JoinColumn(name = "address_id", nullable = false)
  var address: AddressRE,

  @Column(name = "num_of_bedrooms")
  val numOfBedrooms: Int,

  @Column(name = "num_of_wc")
  val numOfWC: Int,

  @Column(name = "floor")
  val floor: Int,

  @OneToOne(cascade = [CascadeType.ALL], optional = false)
  @JoinColumn(name = "parking_info_id", nullable = false)
  var parkingInfo: ParkingInfoRE,

  @Column(name = "is_furnished")
  val furnished: Boolean
) {
  override fun toString(): String {
    return "PropertyRE(id=$id, size=$size, address=$address, numOfBedrooms=$numOfBedrooms, numOfWC=$numOfWC, floor=$floor, parkingInfo=$parkingInfo, furnished=$furnished)"
  }
}
