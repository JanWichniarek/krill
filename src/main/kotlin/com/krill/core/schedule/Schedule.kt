package com.krill.core.schedule

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "schedules")
class Schedule(
    @Column(name = "schedule_id")
    val scheduleId: UUID,
    val title: String?,
) : PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "schedules_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "schedules_seq",
        sequenceName = "schedules_seq",
        allocationSize = 50
    )
    val id: Long = 0
}