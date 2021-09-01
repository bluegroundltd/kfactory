package io.github.bluegroundltd.app

import io.github.bluegroundltd.factories.address.AddressFactory
import io.github.bluegroundltd.factories.address.traits.USTrait
import io.github.bluegroundltd.kfactory.withTrait
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
