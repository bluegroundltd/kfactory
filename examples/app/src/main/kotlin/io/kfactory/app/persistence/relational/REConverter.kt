package io.kfactory.app.persistence.relational

interface REConverter<DomainEntity, REEntity> {
  fun toEntity(domain: DomainEntity): REEntity
  fun toDomain(entity: REEntity): DomainEntity
}
