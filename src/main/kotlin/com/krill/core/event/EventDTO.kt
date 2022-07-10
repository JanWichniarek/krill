package com.krill.core.event

import com.krill.core.category.CategoryDTO
import com.krill.core.schedule.Schedule
import java.time.LocalDateTime
import java.util.stream.Collectors

data class EventDTO(
    val id: Long,
    val title: String,
    val type: EventType,
    val timeType: TimeType,
    val date: LocalDateTime,
    val description: String,
    val categories: Set<CategoryDTO>,
    val priority: Priority,
    val icon: String
) {
    companion object {
        fun from(source: Event): EventDTO = EventDTO(
            source.id ?: 0L,
            source.title,
            source.type,
            source.timeType,
            source.date,
            source.description,
            source.categories.stream().map { CategoryDTO.from(it) }.collect(Collectors.toSet()),
            source.priority,
            source.icon
        )
    }

    fun toEntity(schedule: Schedule): Event = Event(
        title,
        type,
        timeType,
        date,
        description,
        categories.stream().map { it.toEntity() }.collect(Collectors.toSet()),
        priority,
        icon,
        schedule
    )

}

