package io.github.bluegroundltd.app.persistence.relational.customer

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface CustomerRepository : RERepository<CustomerRE>
