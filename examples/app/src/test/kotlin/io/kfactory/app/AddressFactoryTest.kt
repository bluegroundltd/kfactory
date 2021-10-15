package io.kfactory.app

import io.kfactory.core.withTrait
import io.kfactory.factories.address.AddressFactory
import io.kfactory.factories.address.traits.USTrait
import org.junit.Test
import kotlin.test.assertTrue

class AddressFactoryTest {

  @Test
  fun `should have US country when using US trait`() {
    val addressFactory = AddressFactory
      .withTrait(USTrait)
      .produceMany()
      .take(3)
      .toList()

    assertTrue(addressFactory.all { it.country == "US" })
  }
}
