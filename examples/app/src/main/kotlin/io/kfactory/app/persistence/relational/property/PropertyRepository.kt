package io.kfactory.app.persistence.relational.property

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface PropertyRepository : RERepository<PropertyRE>
