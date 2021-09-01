package io.kfactory.app.persistence.relational.address

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface AddressRepository : RERepository<AddressRE>
