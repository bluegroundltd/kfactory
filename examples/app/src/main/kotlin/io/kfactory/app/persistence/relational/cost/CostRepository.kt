package io.kfactory.app.persistence.relational.cost

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface CostRepository : RERepository<CostRE>
