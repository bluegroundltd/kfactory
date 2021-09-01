package io.github.bluegroundltd.app.booking

import io.github.bluegroundltd.app.booking.dto.CreateBookingDTO
import io.github.bluegroundltd.app.persistence.relational.address.AddressRE
import io.github.bluegroundltd.app.persistence.relational.address.AddressREConverter
import io.github.bluegroundltd.app.persistence.relational.address.AddressRepository
import io.github.bluegroundltd.app.persistence.relational.address.isSameAddress
import io.github.bluegroundltd.app.persistence.relational.booking.BookingRE
import io.github.bluegroundltd.app.persistence.relational.booking.BookingREConverter
import io.github.bluegroundltd.app.persistence.relational.booking.BookingRepository
import io.github.bluegroundltd.app.persistence.relational.cost.CostRE
import io.github.bluegroundltd.app.persistence.relational.cost.CostREConverter
import io.github.bluegroundltd.app.persistence.relational.cost.CostRepository
import io.github.bluegroundltd.app.persistence.relational.customer.CustomerRE
import io.github.bluegroundltd.app.persistence.relational.customer.CustomerREConverter
import io.github.bluegroundltd.app.persistence.relational.customer.CustomerRepository
import io.github.bluegroundltd.app.persistence.relational.parking.ParkingInfoRE
import io.github.bluegroundltd.app.persistence.relational.parking.ParkingInfoREConverter
import io.github.bluegroundltd.app.persistence.relational.parking.ParkingInfoRepository
import io.github.bluegroundltd.app.persistence.relational.payment.PaymentMethodRE
import io.github.bluegroundltd.app.persistence.relational.payment.PaymentMethodREConverter
import io.github.bluegroundltd.app.persistence.relational.payment.PaymentMethodRepository
import io.github.bluegroundltd.app.persistence.relational.property.PropertyRE
import io.github.bluegroundltd.app.persistence.relational.property.PropertyREConverter
import io.github.bluegroundltd.app.persistence.relational.property.PropertyRepository
import io.github.bluegroundltd.domain.payment.PaymentType
import io.github.bluegroundltd.factories.booking.BookingFactory
import io.github.bluegroundltd.factories.booking.traits.EndingSoonTrait
import io.github.bluegroundltd.factories.booking.traits.PastBookingTrait
import io.github.bluegroundltd.factories.cost.CostFactory
import io.github.bluegroundltd.factories.customer.CustomerFactory
import io.github.bluegroundltd.factories.payment.PaymentMethodFactory
import io.github.bluegroundltd.factories.property.PropertyFactory
import io.github.bluegroundltd.factories.property.traits.ApartmentSizeTrait
import io.github.bluegroundltd.kfactory.withTrait
import jakarta.inject.Singleton
import javax.transaction.Transactional

@Singleton
open class BookingRelationalService(
  private val addressRepository: AddressRepository,
  private val bookingRepository: BookingRepository,
  private val costRepository: CostRepository,
  private val customerRepository: CustomerRepository,
  private val parkingInfoRepository: ParkingInfoRepository,
  private val paymentMethodRepository: PaymentMethodRepository,
  private val propertyRepository: PropertyRepository
) {

  @Transactional
  open fun fillFixtureData() {
    val addresses = fillAddresses()
    val costs = fillCosts()
    val parkingInfos = fillParkingInfo()
    val customers = fillCustomers()
    val paymentMethods = fillPaymentMethods()
    val properties = fillProperties(addresses, parkingInfos)

    fillBookings(properties, paymentMethods, customers, costs)
  }

  private fun fillAddresses(): List<AddressRE> {
    val addresses = FixtureData.addresses.map { AddressREConverter.toEntity(domain = it) }
    return addressRepository.saveAll(addresses).toList()
  }

  private fun fillCosts(): List<CostRE> {
    val costs = FixtureData.costs.map { CostREConverter.toEntity(domain = it) }
    return costRepository.saveAll(costs).toList()
  }

  private fun fillParkingInfo(): List<ParkingInfoRE> {
    val parkingInfos = FixtureData.parkingInfos.map { ParkingInfoREConverter.toEntity(domain = it) }
    return parkingInfoRepository.saveAll(parkingInfos).toList()
  }

  private fun fillCustomers(): List<CustomerRE> {
    val customers = FixtureData.customers.map { CustomerREConverter.toEntity(domain = it) }
    return customerRepository.saveAll(customers).toList()
  }

  private fun fillPaymentMethods(): List<PaymentMethodRE> {
    val paymentMethods = FixtureData.paymentMethods.map { PaymentMethodREConverter.toEntity(domain = it) }
    return paymentMethodRepository.saveAll(paymentMethods).toList()
  }

  private fun fillProperties(addresses: List<AddressRE>, parkingInfos: List<ParkingInfoRE>): List<PropertyRE> {
    val smallProperties = PropertyFactory
      .withTrait(ApartmentSizeTrait.SMALL)
      .produceMany()
      .take(1)
      .toList()

    val mediumProperties = PropertyFactory
      .withTrait(ApartmentSizeTrait.MEDIUM)
      .produceMany()
      .take(1)
      .toList()

    val largeProperties = PropertyFactory
      .withTrait(ApartmentSizeTrait.LARGE)
      .produceMany()
      .take(1)
      .toList()

    val properties = (smallProperties + mediumProperties + largeProperties).mapIndexed { index, property ->
      PropertyREConverter
        .toEntity(domain = property)
        .apply {
          address = addresses[index]
          parkingInfo = parkingInfos[index]
        }
    }

    return propertyRepository.saveAll(properties).toList()
  }

  private fun fillBookings(
    properties: List<PropertyRE>,
    paymentMethods: List<PaymentMethodRE>,
    customers: List<CustomerRE>,
    costs: List<CostRE>
  ): List<BookingRE> {
    val endingSoonBookings = BookingFactory
      .withTrait(EndingSoonTrait)
      .produceMany()
      .take(1)
      .toList()

    val pastBookings = BookingFactory
      .withTrait(PastBookingTrait)
      .produceMany()
      .take(1)
      .toList()

    val allBookings = endingSoonBookings + pastBookings

    return bookingRepository.saveAll(
      allBookings.mapIndexed { index, booking ->
        BookingREConverter
          .toEntity(domain = booking)
          .apply {
            property = properties[index]
            paymentMethod = paymentMethods[index]
            customer = customers[index]
            cost = costs[index]
          }
      }
    ).toList()
  }

  @Transactional
  open fun book(bookingDTO: CreateBookingDTO) {
    val property = propertyRepository.findAll()
      .find { it.address.isSameAddress(bookingDTO.propertyAddress) }
      ?: PropertyREConverter.toEntity(PropertyFactory.produce())

    val paymentMethod = paymentMethodRepository.findAll()
      .find { it.type == PaymentType.from(rawName = bookingDTO.paymentType) }
      ?: PaymentMethodREConverter.toEntity(PaymentMethodFactory.produce())

    val customer = customerRepository.findAll()
      .find { it.email == bookingDTO.customerEmail }
      ?: CustomerREConverter.toEntity(CustomerFactory.produce())

    val cost = costRepository.findAll()
      .find { it.amount == bookingDTO.paymentAmount }
      ?: CostREConverter.toEntity(CostFactory.produce())

    val booking = BookingFactory
      .withCheckInDate(checkInDate = bookingDTO.checkInDate)
      .withCheckOutDate(checkOutDate = bookingDTO.checkOutDate)
      .withProperty(property = PropertyREConverter.toDomain(property))
      .withPaymentMethod(paymentMethod = PaymentMethodREConverter.toDomain(paymentMethod))
      .withCustomer(customer = CustomerREConverter.toDomain(customer))
      .withCost(cost = CostREConverter.toDomain(cost))
      .produceMany()
      .take(1)
      .toList()
      .first()

    bookingRepository.save(BookingREConverter.toEntity(domain = booking))
  }
}
