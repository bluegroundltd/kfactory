package io.kfactory.app.persistence.nonrelational.payment

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.kfactory.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
open class PaymentMethodNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<PaymentMethodNRE> {
  override val collection: MongoCollection<PaymentMethodNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("paymentMethods", PaymentMethodNRE::class.java)
}
