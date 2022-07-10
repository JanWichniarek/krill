package com.krill.core.schedule

import com.krill.core.event.EventDTO
import com.krill.core.event.EventRepository
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors
import javax.enterprise.context.RequestScoped
import javax.transaction.Transactional

@RequestScoped
class ScheduleService
    (val scheduleRepository: ScheduleRepository, val eventRepository: EventRepository) {
    @Transactional
    fun create(): ScheduleDTO {
        val schedule = Schedule(UUID.randomUUID(), null)
        schedule.persist()
        return ScheduleDTO.from(schedule)
    }

    @Transactional
    fun get(scheduleId: String, eventsFrom: LocalDate?, eventsTo: LocalDate?): ScheduleDTO? {
        val schedule = scheduleRepository.findByScheduleId(UUID.fromString(scheduleId))
        if (schedule != null) {
            val events =
                eventRepository.findByScheduleId(UUID.fromString(scheduleId), eventsFrom, eventsTo).stream().map { EventDTO.from(it) }
                    .collect(
                        Collectors.toList()
                    )
            val scheduleDTO = ScheduleDTO.from(schedule)
            scheduleDTO.events = events
            return scheduleDTO
        }
        return null
    }

}
