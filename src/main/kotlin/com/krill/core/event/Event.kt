package com.krill.core.event

import com.krill.core.category.Category
import com.krill.core.schedule.Schedule
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "events")
class Event(
    val title: String,
    @Enumerated(EnumType.STRING)
    val type: EventType,
    @Enumerated(EnumType.STRING)
    val timeType: TimeType,
    val date: LocalDateTime,
    val description: String,
    @ManyToMany
    val categories: Set<Category>,
    @Enumerated(EnumType.STRING)
    val priority: Priority,
    val icon: String,
    @JoinColumn(name = "schedule_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val schedule: Schedule
) : PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "events_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "events_seq",
        sequenceName = "events_seq",
        allocationSize = 50
    )
    val id: Long = 0
}