package io.github.bluegroundltd.app.persistence.nonrelational.cost

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.github.bluegroundltd.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
open class CostNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<CostNRE> {
  override val collection: MongoCollection<CostNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("costs", CostNRE::class.java)
}
