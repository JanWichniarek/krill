package com.krill.dto

import com.krill.entity.EventType
import com.krill.entity.Priority
import com.krill.entity.TimeType
import java.time.LocalDateTime

data class EventDTO(val title: String,
                    val type: EventType,
                    val timeType: TimeType,
                    val date: LocalDateTime,
                    val description: String,
                    val categories: Set<CategoryDTO>,
                    val priority: Priority,
                    val icon: String)
