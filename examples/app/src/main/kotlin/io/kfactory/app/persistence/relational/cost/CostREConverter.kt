package io.kfactory.app.persistence.relational.cost

import io.kfactory.app.persistence.relational.REConverter
import io.kfactory.domain.Cost

object CostREConverter : REConverter<Cost, CostRE> {
  override fun toEntity(domain: Cost): CostRE = CostRE(
    amount = domain.amount,
    currency = domain.currency
  )

  override fun toDomain(entity: CostRE): Cost = Cost(
    amount = entity.amount,
    currency = entity.currency
  )
}
