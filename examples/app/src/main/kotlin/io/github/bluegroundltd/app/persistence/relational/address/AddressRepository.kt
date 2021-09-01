package io.github.bluegroundltd.app.persistence.relational.address

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface AddressRepository : RERepository<AddressRE>
