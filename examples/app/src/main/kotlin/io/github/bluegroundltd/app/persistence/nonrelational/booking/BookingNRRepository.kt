package io.github.bluegroundltd.app.persistence.nonrelational.booking

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.github.bluegroundltd.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
open class BookingNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<BookingNRE> {
  override val collection: MongoCollection<BookingNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("bookings", BookingNRE::class.java)
}
