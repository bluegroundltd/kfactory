package io.kfactory.app.persistence.relational.parking

import io.kfactory.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface ParkingInfoRepository : RERepository<ParkingInfoRE>
