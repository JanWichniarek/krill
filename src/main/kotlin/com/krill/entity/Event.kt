package com.krill.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
class Event(
        val title: String,
        val type: EventType,
        val timeType: TimeType,
        val date: LocalDate,
        val description: String,
        @OneToMany
        val categories: Set<Category>,
        val priority: Priority,
        val icon: String
) : PanacheEntity()