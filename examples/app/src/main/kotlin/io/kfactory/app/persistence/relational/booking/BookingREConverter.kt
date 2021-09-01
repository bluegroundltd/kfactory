package io.kfactory.app.persistence.relational.booking

import io.kfactory.app.persistence.relational.REConverter
import io.kfactory.app.persistence.relational.cost.CostREConverter
import io.kfactory.app.persistence.relational.customer.CustomerREConverter
import io.kfactory.app.persistence.relational.payment.PaymentMethodREConverter
import io.kfactory.app.persistence.relational.property.PropertyREConverter
import io.kfactory.domain.Booking

object BookingREConverter : REConverter<Booking, BookingRE> {
  override fun toEntity(domain: Booking): BookingRE = BookingRE(
    checkInDate = domain.checkInDate,
    checkOutDate = domain.checkOutDate,
    property = PropertyREConverter.toEntity(domain = domain.property),
    paymentMethod = PaymentMethodREConverter.toEntity(domain = domain.paymentMethod),
    customer = CustomerREConverter.toEntity(domain = domain.customer),
    cost = CostREConverter.toEntity(domain = domain.cost)
  )

  override fun toDomain(entity: BookingRE): Booking = Booking(
    checkInDate = entity.checkInDate,
    checkOutDate = entity.checkOutDate,
    property = PropertyREConverter.toDomain(entity = entity.property),
    paymentMethod = PaymentMethodREConverter.toDomain(entity = entity.paymentMethod),
    customer = CustomerREConverter.toDomain(entity = entity.customer),
    cost = CostREConverter.toDomain(entity = entity.cost)
  )
}
