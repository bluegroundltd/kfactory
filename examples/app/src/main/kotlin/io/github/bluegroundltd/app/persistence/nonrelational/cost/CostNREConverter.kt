package io.github.bluegroundltd.app.persistence.nonrelational.cost

import io.github.bluegroundltd.app.persistence.nonrelational.NREConverter
import io.github.bluegroundltd.domain.Cost

object CostNREConverter : NREConverter<Cost, CostNRE> {
  override fun toEntity(domain: Cost): CostNRE = CostNRE(
    amount = domain.amount,
    currency = domain.currency
  )

  override fun toDomain(entity: CostNRE): Cost = Cost(
    amount = entity.amount,
    currency = entity.currency
  )
}
