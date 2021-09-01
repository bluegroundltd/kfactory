package io.github.bluegroundltd.factories.booking

import com.github.javafaker.Faker
import io.github.bluegroundltd.domain.*
import io.github.bluegroundltd.factories.cost.CostFactory
import io.github.bluegroundltd.factories.customer.CustomerFactory
import io.github.bluegroundltd.factories.payment.PaymentMethodFactory
import io.github.bluegroundltd.factories.property.PropertyFactory
import io.github.bluegroundltd.kfactory.Factory
import io.github.bluegroundltd.kfactory.TraitEnhancedFactory
import io.github.bluegroundltd.kfactory.Yielded
import java.util.concurrent.TimeUnit

open class BookingFactory(
  private var property: Yielded<Property> = { PropertyFactory.produce() },
  private var checkInDate: Yielded<String> = { Faker().date().future(1, TimeUnit.DAYS).toString() },
  private var checkOutDate: Yielded<String> = { Faker().date().future(10, TimeUnit.DAYS).toString() },
  private var paymentMethod: Yielded<PaymentMethod> = { PaymentMethodFactory.produce() },
  private var customer: Yielded<Customer> = { CustomerFactory.produce() },
  private var cost: Yielded<Cost> = { CostFactory.produce() }
) : Factory<Booking>, TraitEnhancedFactory {

  companion object Default : BookingFactory()

  fun withProperty(property: Property) = apply {
    this.property = { property }
  }

  fun withProperty(property: Yielded<Property>) = apply {
    this.property = property
  }

  fun withCheckInDate(checkInDate: String) = apply {
    this.checkInDate = { checkInDate }
  }

  fun withCheckInDate(checkInDate: Yielded<String>) = apply {
    this.checkInDate = checkInDate
  }

  fun withCheckOutDate(checkOutDate: String) = apply {
    this.checkOutDate = { checkOutDate }
  }

  fun withCheckOutDate(checkOutDate: Yielded<String>) = apply {
    this.checkOutDate = checkOutDate
  }

  fun withPaymentMethod(paymentMethod: PaymentMethod) = apply {
    this.paymentMethod = { paymentMethod }
  }

  fun withPaymentMethod(paymentMethod: Yielded<PaymentMethod>) = apply {
    this.paymentMethod = paymentMethod
  }

  fun withCustomer(customer: Customer) = apply {
    this.customer = { customer }
  }

  fun withCustomer(customer: Yielded<Customer>) = apply {
    this.customer = customer
  }

  fun withCost(cost: Cost) = apply {
    this.cost = { cost }
  }

  fun withCost(cost: Yielded<Cost>) = apply {
    this.cost = cost
  }

  override fun produce(): Booking = Booking(
    property = property(),
    checkInDate = checkInDate(),
    checkOutDate = checkOutDate(),
    paymentMethod = paymentMethod(),
    customer = customer(),
    cost = cost()
  )
}
