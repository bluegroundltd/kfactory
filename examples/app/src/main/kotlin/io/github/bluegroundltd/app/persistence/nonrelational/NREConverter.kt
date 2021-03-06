package io.github.bluegroundltd.app.persistence.nonrelational

interface NREConverter<DomainEntity, NREEntity> {
  fun toEntity(domain: DomainEntity): NREEntity
  fun toDomain(entity: NREEntity): DomainEntity
}
