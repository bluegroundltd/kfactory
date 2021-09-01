package io.github.bluegroundltd.app.persistence.relational.property

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface PropertyRepository : RERepository<PropertyRE>
