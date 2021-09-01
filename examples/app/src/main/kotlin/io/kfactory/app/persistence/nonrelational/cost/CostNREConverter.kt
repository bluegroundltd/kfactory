package io.kfactory.app.persistence.nonrelational.cost

import io.kfactory.app.persistence.nonrelational.NREConverter
import io.kfactory.domain.Cost

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
