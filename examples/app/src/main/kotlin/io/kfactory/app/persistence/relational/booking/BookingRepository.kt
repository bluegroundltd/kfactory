package io.kfactory.app.persistence.relational.booking

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface BookingRepository : RERepository<BookingRE>
