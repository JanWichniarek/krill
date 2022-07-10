package com.krill.core.schedule

import com.krill.core.event.Event
import com.krill.core.event.EventDTO
import java.util.*

data class ScheduleDTO(
    val scheduleId: UUID,
    val title: String?,
    var events: List<EventDTO>?
) {
    companion object {
        fun from(source: Schedule): ScheduleDTO = ScheduleDTO(source.scheduleId, source.title, null)
        fun from(source: Schedule, events: List<EventDTO>): ScheduleDTO = ScheduleDTO(source.scheduleId, source.title, events)
    }
}
