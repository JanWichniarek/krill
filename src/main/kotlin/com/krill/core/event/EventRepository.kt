package com.krill.core.event

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.quarkus.panache.common.Parameters
import java.sql.Timestamp
import java.time.LocalDate
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EventRepository : PanacheRepository<Event> {

    fun findByScheduleId(scheduleId: UUID, dateFrom: LocalDate?, dateTo: LocalDate?): List<Event> =
        find(
            """
                schedule.scheduleId = :scheduleId and 
                (
                    (cast(:dateFrom as timestamp) is null or date>=:dateFrom)
                    and
                    (cast(:dateTo as timestamp) is null or date<=:dateTo)
                )
                """,
            Parameters
                .with("scheduleId", scheduleId)
                .and("dateFrom", dateFrom?.atStartOfDay())
                .and("dateTo", dateTo?.atStartOfDay())
        )
            .list()

}