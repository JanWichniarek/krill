package com.krill.core.event

import com.krill.core.schedule.ScheduleNotFoundException
import com.krill.core.schedule.ScheduleRepository
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.transaction.Transactional

@RequestScoped
class EventService(private val scheduleRepository: ScheduleRepository) {

    @Transactional
    fun create(scheduleId: UUID, eventDTO: EventDTO): EventDTO {
        scheduleRepository.findByScheduleId(scheduleId)?.let {
            val event = eventDTO.toEntity(it)
            event.persist()
            return EventDTO.from(event)
        }
        throw ScheduleNotFoundException(scheduleId)
    }
}