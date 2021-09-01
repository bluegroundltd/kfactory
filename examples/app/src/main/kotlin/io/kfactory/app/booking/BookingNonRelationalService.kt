package io.kfactory.app.booking

import io.kfactory.app.booking.dto.CreateBookingDTO
import io.kfactory.app.persistence.nonrelational.address.*
import io.kfactory.app.persistence.nonrelational.booking.BookingNRE
import io.kfactory.app.persistence.nonrelational.booking.BookingNREConverter
import io.kfactory.app.persistence.nonrelational.booking.BookingNRRepository
import io.kfactory.app.persistence.nonrelational.cost.CostNRE
import io.kfactory.app.persistence.nonrelational.cost.CostNREConverter
import io.kfactory.app.persistence.nonrelational.cost.CostNRRepository
import io.kfactory.app.persistence.nonrelational.customer.CustomerNRE
import io.kfactory.app.persistence.nonrelational.customer.CustomerNREConverter
import io.kfactory.app.persistence.nonrelational.customer.CustomerNRRepository
import io.kfactory.app.persistence.nonrelational.parking.ParkingInfoNRE
import io.kfactory.app.persistence.nonrelational.parking.ParkingInfoNREConverter
import io.kfactory.app.persistence.nonrelational.parking.ParkingInfoNRRepository
import io.kfactory.app.persistence.nonrelational.payment.PaymentMethodNRE
import io.kfactory.app.persistence.nonrelational.payment.PaymentMethodNREConverter
import io.kfactory.app.persistence.nonrelational.payment.PaymentMethodNRRepository
import io.kfactory.app.persistence.nonrelational.property.PropertyNRE
import io.kfactory.app.persistence.nonrelational.property.PropertyNREConverter
import io.kfactory.app.persistence.nonrelational.property.PropertyNRRepository
import io.kfactory.core.withTrait
import io.kfactory.domain.payment.PaymentType
import io.kfactory.factories.booking.BookingFactory
import io.kfactory.factories.booking.traits.EndingSoonTrait
import io.kfactory.factories.booking.traits.PastBookingTrait
import io.kfactory.factories.cost.CostFactory
import io.kfactory.factories.customer.CustomerFactory
import io.kfactory.factories.payment.PaymentMethodFactory
import io.kfactory.factories.property.PropertyFactory
import io.kfactory.factories.property.traits.ApartmentSizeTrait
import jakarta.inject.Singleton

@Singleton
class BookingNonRelationalService(
  private val addressRepository: AddressNRRepository,
  private val bookingRepository: BookingNRRepository,
  private val costRepository: CostNRRepository,
  private val customerRepository: CustomerNRRepository,
  private val parkingInfoRepository: ParkingInfoNRRepository,
  private val paymentMethodRepository: PaymentMethodNRRepository,
  private val propertyRepository: PropertyNRRepository
) {

  fun fillFixtureData() {
    val addresses = fillAddresses()
    val costs = fillCosts()
    val parkingInfos = fillParkingInfo()
    val customers = fillCustomers()
    val paymentMethods = fillPaymentMethods()
    val properties = fillProperties(addresses, parkingInfos)

    fillBookings(properties, paymentMethods, customers, costs)
  }

  private fun fillAddresses(): List<AddressNRE> {
    val addresses = FixtureData.addresses.map { AddressNREConverter.toEntity(domain = it) }
    return addresses.also { addressRepository.saveAll(it) }
  }

  private fun fillCosts(): List<CostNRE> {
    val costs = FixtureData.costs.map { CostNREConverter.toEntity(domain = it) }
    return costs.also { costRepository.saveAll(it) }
  }

  private fun fillParkingInfo(): List<ParkingInfoNRE> {
    val parkingInfos = FixtureData.parkingInfos.map { ParkingInfoNREConverter.toEntity(domain = it) }
    return parkingInfos.also { parkingInfoRepository.saveAll(it) }
  }

  private fun fillCustomers(): List<CustomerNRE> {
    val customers = FixtureData.customers.map { CustomerNREConverter.toEntity(domain = it) }
    return customers.also { customerRepository.saveAll(it) }
  }

  private fun fillPaymentMethods(): List<PaymentMethodNRE> {
    val paymentMethods = FixtureData.paymentMethods.map { PaymentMethodNREConverter.toEntity(domain = it) }
    return paymentMethods.also { paymentMethodRepository.saveAll(it) }
  }

  private fun fillProperties(addresses: List<AddressNRE>, parkingInfos: List<ParkingInfoNRE>): List<PropertyNRE> {
    val smallProperties = PropertyFactory
      .withTrait(ApartmentSizeTrait.SMALL)
      .withAddress(AddressNREConverter.toDomain(addresses.random()))
      .withParkingInfo(ParkingInfoNREConverter.toDomain(parkingInfos.random()))
      .produceMany()
      .take(1)
      .toList()

    val mediumProperties = PropertyFactory
      .withTrait(ApartmentSizeTrait.MEDIUM)
      .withAddress(AddressNREConverter.toDomain(addresses.random()))
      .withParkingInfo(ParkingInfoNREConverter.toDomain(parkingInfos.random()))
      .produceMany()
      .take(1)
      .toList()

    val largeProperties = PropertyFactory
      .withTrait(ApartmentSizeTrait.LARGE)
      .withAddress(AddressNREConverter.toDomain(addresses.random()))
      .withParkingInfo(ParkingInfoNREConverter.toDomain(parkingInfos.random()))
      .produceMany()
      .take(1)
      .toList()

    return (smallProperties + mediumProperties + largeProperties)
      .map { PropertyNREConverter.toEntity(domain = it) }
      .also { propertyRepository.saveAll(it) }
  }

  private fun fillBookings(
    properties: List<PropertyNRE>,
    paymentMethods: List<PaymentMethodNRE>,
    customers: List<CustomerNRE>,
    costs: List<CostNRE>
  ): List<BookingNRE> {
    val endingSoonBookings = BookingFactory
      .withTrait(EndingSoonTrait)
      .withProperty(PropertyNREConverter.toDomain(properties.random()))
      .withPaymentMethod(PaymentMethodNREConverter.toDomain(paymentMethods.random()))
      .withCustomer(CustomerNREConverter.toDomain(customers.random()))
      .withCost(CostNREConverter.toDomain(costs.random()))
      .produceMany()
      .take(1)
      .toList()

    val pastBookings = BookingFactory
      .withTrait(PastBookingTrait)
      .withProperty(PropertyNREConverter.toDomain(properties.random()))
      .withPaymentMethod(PaymentMethodNREConverter.toDomain(paymentMethods.random()))
      .withCustomer(CustomerNREConverter.toDomain(customers.random()))
      .withCost(CostNREConverter.toDomain(costs.random()))
      .produceMany()
      .take(1)
      .toList()

    val allBookings = endingSoonBookings + pastBookings

    return allBookings
      .map { BookingNREConverter.toEntity(domain = it) }
      .also { bookingRepository.saveAll(it) }
  }

  fun book(bookingDTO: CreateBookingDTO) {
    val property = propertyRepository.findAll()
      .find { it.address.isSameAddress(bookingDTO.propertyAddress) }
      ?: PropertyNREConverter.toEntity(PropertyFactory.produce())

    val paymentMethod = paymentMethodRepository.findAll()
      .find { it.type == PaymentType.from(rawName = bookingDTO.paymentType) }
      ?: PaymentMethodNREConverter.toEntity(PaymentMethodFactory.produce())

    val customer = customerRepository.findAll()
      .find { it.email == bookingDTO.customerEmail }
      ?: CustomerNREConverter.toEntity(CustomerFactory.produce())

    val cost = costRepository.findAll()
      .find { it.amount == bookingDTO.paymentAmount }
      ?: CostNREConverter.toEntity(CostFactory.produce())

    val bookings = BookingFactory
      .withCheckInDate(newCheckInDate = bookingDTO.checkInDate)
      .withCheckOutDate(newCheckOutDate = bookingDTO.checkOutDate)
      .withProperty(newProperty = PropertyNREConverter.toDomain(property))
      .withPaymentMethod(newPaymentMethod = PaymentMethodNREConverter.toDomain(paymentMethod))
      .withCustomer(newCustomer = CustomerNREConverter.toDomain(customer))
      .withCost(newCost = CostNREConverter.toDomain(cost))
      .buildMany()
      .take(1)
      .toList()

    bookingRepository.saveAll(bookings.map { BookingNREConverter.toEntity(domain = it) })
  }
}
