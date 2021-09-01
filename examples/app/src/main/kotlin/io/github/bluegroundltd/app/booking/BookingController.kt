package io.github.bluegroundltd.app.booking

import io.github.bluegroundltd.app.booking.dto.CreateBookingDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/booking")
class BookingController(
  private val bookingRelationalService: BookingRelationalService,
  private val bookingNonRelationalService: BookingNonRelationalService
) {

  @Post(value = "/book")
  @Status(HttpStatus.CREATED)
  fun createBooking(@Body createBookingDTO: CreateBookingDTO): HttpResponse<Any> {
    bookingRelationalService.book(bookingDTO = createBookingDTO)
    bookingNonRelationalService.book(bookingDTO = createBookingDTO)
    return HttpResponse.status(HttpStatus.CREATED)
  }

  @Post(value = "/create-fixture-data")
  @Status(HttpStatus.CREATED)
  fun fillFixtureData(): HttpResponse<Any> {
    bookingRelationalService.fillFixtureData()
    bookingNonRelationalService.fillFixtureData()
    return HttpResponse.status(HttpStatus.CREATED)
  }
}
