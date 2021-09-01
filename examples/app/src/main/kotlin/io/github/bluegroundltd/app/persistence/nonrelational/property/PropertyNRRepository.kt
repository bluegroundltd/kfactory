package io.github.bluegroundltd.app.persistence.nonrelational.property

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.github.bluegroundltd.app.persistence.nonrelational.NRRepository
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
open class PropertyNRRepository(
  @Property(name = "mongodb.dbname")
  private val mongoDBName: String,
  private val mongoClient: MongoClient
) : NRRepository<PropertyNRE> {
  override val collection: MongoCollection<PropertyNRE>
    get() = mongoClient
      .getDatabase(mongoDBName)
      .getCollection("properties", PropertyNRE::class.java)
}
