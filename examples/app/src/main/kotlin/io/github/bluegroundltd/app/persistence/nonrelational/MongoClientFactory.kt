package io.github.bluegroundltd.app.persistence.nonrelational

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.micronaut.context.annotation.*
import jakarta.inject.Singleton
import org.bson.UuidRepresentation
import org.bson.codecs.UuidCodec
import org.bson.codecs.configuration.CodecRegistries.*
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider

@Factory
@Requires(classes = [MongoClient::class])
open class MongoClientFactory(
  @Value("\${mongodb.uri}") private val mongoUri: String
) {

  private val connectionString: ConnectionString
    get() = ConnectionString(mongoUri)

  private val codecRegistry: CodecRegistry
    get() = fromRegistries(
      fromCodecs(UuidCodec(UuidRepresentation.STANDARD)),
      MongoClientSettings.getDefaultCodecRegistry(),
      fromProviders(PojoCodecProvider.builder().automatic(true).build())
    )

  @Bean(preDestroy = "close")
  @Primary
  @Singleton
  open fun mongoClient(): MongoClient {
    val settings = getSettings(codecRegistry, connectionString)
    return MongoClients.create(settings)
  }

  private fun getSettings(
    pojoCodecRegistry: CodecRegistry,
    connectionString: ConnectionString
  ): MongoClientSettings = MongoClientSettings.builder()
    .codecRegistry(pojoCodecRegistry)
    .applyConnectionString(connectionString)
    .build()
}
