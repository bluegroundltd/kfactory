package io.github.bluegroundltd.domain

data class Booking(
  val property: Property,
  val checkInDate: String,
  val checkOutDate: String,
  val paymentMethod: PaymentMethod,
  val customer: Customer,
  val cost: Cost
)
