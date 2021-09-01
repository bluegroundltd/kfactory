package io.kfactory.app.persistence.relational.payment

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface PaymentMethodRepository : RERepository<PaymentMethodRE>
