package io.github.bluegroundltd.app.persistence.relational.cost

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface CostRepository : RERepository<CostRE>
