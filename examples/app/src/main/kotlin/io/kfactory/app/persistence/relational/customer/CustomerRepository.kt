package io.kfactory.app.persistence.relational.customer

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface CustomerRepository : RERepository<CustomerRE>
