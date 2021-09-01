package io.github.bluegroundltd.app.persistence.nonrelational

import com.mongodb.client.MongoCollection
import com.mongodb.client.result.InsertManyResult

/**
 * Base interface for repositories operating in non-relational datastore.
 * */
interface NRRepository<NREEntity> {
  val collection: MongoCollection<NREEntity>

  fun saveAll(entities: List<NREEntity>): InsertManyResult = collection.insertMany(entities)
  fun findAll(): List<NREEntity> = collection.find().toList()
}
