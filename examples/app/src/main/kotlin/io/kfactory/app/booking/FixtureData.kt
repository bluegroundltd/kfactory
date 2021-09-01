package io.kfactory.app.booking

import io.kfactory.core.withTrait
import io.kfactory.domain.*
import io.kfactory.factories.address.AddressFactory
import io.kfactory.factories.address.traits.JapanTrait
import io.kfactory.factories.address.traits.SpainTrait
import io.kfactory.factories.address.traits.USTrait
import io.kfactory.factories.cost.CostFactory
import io.kfactory.factories.cost.traits.EuroCurrencyTrait
import io.kfactory.factories.cost.traits.USDCurrencyTrait
import io.kfactory.factories.customer.CustomerFactory
import io.kfactory.factories.parkinginfo.ParkingInfoFactory
import io.kfactory.factories.parkinginfo.traits.CoveredParkingTrait
import io.kfactory.factories.payment.PaymentMethodFactory
import io.kfactory.factories.payment.traits.CCMasterCardTrait
import io.kfactory.factories.payment.traits.PaypalTrait

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
