package io.github.bluegroundltd.app.persistence.relational

import io.micronaut.data.repository.CrudRepository
import java.util.*

interface RERepository<REEntity> : CrudRepository<REEntity, UUID>
