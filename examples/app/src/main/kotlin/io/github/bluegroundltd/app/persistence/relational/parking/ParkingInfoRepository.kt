package io.github.bluegroundltd.app.persistence.relational.parking

import io.github.bluegroundltd.app.persistence.relational.RERepository
import io.micronaut.data.annotation.Repository

@Repository
interface ParkingInfoRepository : RERepository<ParkingInfoRE>
