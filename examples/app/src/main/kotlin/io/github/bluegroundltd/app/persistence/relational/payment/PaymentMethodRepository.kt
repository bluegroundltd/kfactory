package io.github.bluegroundltd.app.persistence.relational.payment

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface PaymentMethodRepository : RERepository<PaymentMethodRE>
