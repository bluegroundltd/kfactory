package io.github.bluegroundltd.app.persistence.nonrelational.address

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.github.bluegroundltd.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
class AddressNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<AddressNRE> {
  override val collection: MongoCollection<AddressNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("addresses", AddressNRE::class.java)
}
