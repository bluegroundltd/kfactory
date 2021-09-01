package io.github.bluegroundltd.app.booking.dto

import io.micronaut.core.annotation.Introspected

@Introspected
data class CreateBookingDTO(
  val propertyAddress: String,
  val checkInDate: String,
  val checkOutDate: String,
  val paymentType: String,
  val customerEmail: String,
  val paymentAmount: Int
) {
  constructor() : this(
    "",
    "",
    "",
    "",
    "",
    -1
  )
}
