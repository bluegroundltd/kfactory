package io.kfactory.app.persistence.nonrelational.parking

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.kfactory.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
open class ParkingInfoNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<ParkingInfoNRE> {
  override val collection: MongoCollection<ParkingInfoNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("parkingInfos", ParkingInfoNRE::class.java)
}
