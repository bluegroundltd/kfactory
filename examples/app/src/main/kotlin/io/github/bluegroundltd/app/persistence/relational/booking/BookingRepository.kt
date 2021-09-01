package io.github.bluegroundltd.app.persistence.relational.booking

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface BookingRepository : RERepository<BookingRE>
