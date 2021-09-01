package io.github.bluegroundltd.app.persistence.nonrelational.customer

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.github.bluegroundltd.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
open class CustomerNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<CustomerNRE> {
  override val collection: MongoCollection<CustomerNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("customers", CustomerNRE::class.java)
}
