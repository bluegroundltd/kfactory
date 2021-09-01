package io.github.bluegroundltd.app.persistence.nonrelational.booking

import io.github.bluegroundltd.app.persistence.nonrelational.NREConverter
import io.github.bluegroundltd.app.persistence.nonrelational.cost.CostNREConverter
import io.github.bluegroundltd.app.persistence.nonrelational.customer.CustomerNREConverter
import io.github.bluegroundltd.app.persistence.nonrelational.payment.PaymentMethodNREConverter
import io.github.bluegroundltd.app.persistence.nonrelational.property.PropertyNREConverter
import io.github.bluegroundltd.domain.Booking

object BookingNREConverter : NREConverter<Booking, BookingNRE> {
  override fun toEntity(domain: Booking): BookingNRE = BookingNRE(
    checkInDate = domain.checkInDate,
    checkOutDate = domain.checkOutDate,
    property = PropertyNREConverter.toEntity(domain = domain.property),
    paymentMethod = PaymentMethodNREConverter.toEntity(domain = domain.paymentMethod),
    customer = CustomerNREConverter.toEntity(domain = domain.customer),
    cost = CostNREConverter.toEntity(domain = domain.cost)
  )

  override fun toDomain(entity: BookingNRE): Booking = Booking(
    checkInDate = entity.checkInDate,
    checkOutDate = entity.checkOutDate,
    property = PropertyNREConverter.toDomain(entity = entity.property),
    paymentMethod = PaymentMethodNREConverter.toDomain(entity = entity.paymentMethod),
    customer = CustomerNREConverter.toDomain(entity = entity.customer),
    cost = CostNREConverter.toDomain(entity = entity.cost)
  )
}
