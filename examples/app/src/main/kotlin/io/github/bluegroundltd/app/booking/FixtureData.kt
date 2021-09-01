package io.github.bluegroundltd.app.booking

import io.github.bluegroundltd.domain.*
import io.github.bluegroundltd.factories.address.AddressFactory
import io.github.bluegroundltd.factories.address.traits.JapanTrait
import io.github.bluegroundltd.factories.address.traits.SpainTrait
import io.github.bluegroundltd.factories.address.traits.USTrait
import io.github.bluegroundltd.factories.cost.CostFactory
import io.github.bluegroundltd.factories.cost.traits.EuroCurrencyTrait
import io.github.bluegroundltd.factories.cost.traits.USDCurrencyTrait
import io.github.bluegroundltd.factories.customer.CustomerFactory
import io.github.bluegroundltd.factories.parkinginfo.ParkingInfoFactory
import io.github.bluegroundltd.factories.parkinginfo.traits.CoveredParkingTrait
import io.github.bluegroundltd.factories.payment.PaymentMethodFactory
import io.github.bluegroundltd.factories.payment.traits.CCMasterCardTrait
import io.github.bluegroundltd.factories.payment.traits.PaypalTrait
import io.github.bluegroundltd.kfactory.withTrait

object FixtureData {
  val addresses: List<Address> = listOf(
    AddressFactory
      .withTrait(USTrait)
      .produceMany()
      .take(2)
      .toList(),
    AddressFactory
      .withTrait(SpainTrait)
      .produceMany()
      .take(2)
      .toList(),
    AddressFactory
      .withTrait(JapanTrait)
      .produceMany()
      .take(2)
      .toList()
  ).flatten()

  val costs: List<Cost> = listOf(
    CostFactory
      .withTrait(EuroCurrencyTrait)
      .produceMany()
      .take(2)
      .toList(),
    CostFactory
      .withTrait(USDCurrencyTrait)
      .produceMany()
      .take(2)
      .toList()
  ).flatten()

  val parkingInfos: List<ParkingInfo> = listOf(
    ParkingInfoFactory
      .withTrait(CoveredParkingTrait)
      .produceMany()
      .take(3)
      .toList()
  ).flatten()

  val customers: List<Customer> = listOf(
    CustomerFactory
      .produceMany()
      .take(3)
      .toList()
  ).flatten()

  val paymentMethods: List<PaymentMethod> = listOf(
    PaymentMethodFactory
      .withTrait(CCMasterCardTrait)
      .produceMany()
      .take(2)
      .toList(),
    PaymentMethodFactory
      .withTrait(PaypalTrait)
      .produceMany()
      .take(2)
      .toList()
  ).flatten()
}
