package com.krill.core.schedule

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ScheduleRepository : PanacheRepository<Schedule> {

    fun findByScheduleId(scheduleId: UUID): Schedule? = find("scheduleId", scheduleId).firstResult()

}