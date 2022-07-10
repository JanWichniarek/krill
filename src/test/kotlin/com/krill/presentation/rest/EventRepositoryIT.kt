package com.krill.presentation.rest

import com.krill.PostgresResource
import com.krill.core.event.*
import com.krill.core.schedule.Schedule
import com.krill.core.schedule.ScheduleRepository
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional


@QuarkusTest
@QuarkusTestResource(
    PostgresResource::class
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventRepositoryIT {

    @Inject
    lateinit var eventRepository: EventRepository
    @Inject
    lateinit var scheduleRepository: ScheduleRepository

    companion object {
        val SCHEDULE_ID_1 = UUID(0, 0)
        val SCHEDULE_ID_2 = UUID(0, 1)
        val schedule1 = Schedule(SCHEDULE_ID_1, null)
        val event1 = createEvent(LocalDate.of(2022, 1, 1))
        val event2 = createEvent(LocalDate.of(2022, 1, 2))
        val event3 = createEvent(LocalDate.of(2022, 2, 1))
        val event4 = createEvent(LocalDate.of(2022, 3, 1))
        val event5 = createEvent(LocalDate.of(2022, 3, 31))
        val event6 = createEvent(LocalDate.of(2022, 4, 1))

        private fun createEvent(date: LocalDate) = Event(
            "title",
            EventType.ONE_TIME,
            TimeType.POINT,
            date.atStartOfDay(),
            "description",
            emptySet(),
            Priority.MEDIUM,
            "icon",
            schedule1
        )
    }

    @Transactional
    @BeforeAll
    fun setUp() {
        schedule1.persist()
        event1.persist()
        event2.persist()
        event3.persist()
        event4.persist()
        event5.persist()
        event6.persist()
    }

    @Transactional
    @AfterAll
    fun tearDown() {
        eventRepository.deleteAll()
        scheduleRepository.deleteAll()
    }

    fun testSourceData(): List<Arguments> {
        val dateFrom = LocalDate.of(2022, 1, 2)
        val dateTo = LocalDate.of(2022, 3, 31)
        return listOf(
            Arguments.of(SCHEDULE_ID_1, null, null, 6),
            Arguments.of(SCHEDULE_ID_1, dateFrom, null, 5),
            Arguments.of(SCHEDULE_ID_1, null, dateTo, 5),
            Arguments.of(SCHEDULE_ID_1, dateFrom, dateTo, 4),
            Arguments.of(SCHEDULE_ID_2, null, null, 0)
        )
    }

    @ParameterizedTest
    @MethodSource("testSourceData")
    fun test(scheduleId: UUID, dateFrom: LocalDate?, dateTo: LocalDate?, expectedEventsNumber: Int) {
        val retrievedEvents = eventRepository.findByScheduleId(scheduleId, dateFrom, dateTo)

        assertThat(retrievedEvents.size).isEqualTo(expectedEventsNumber)
        dateFrom?.let { referenceDate -> retrievedEvents.stream().allMatch { it.date.isAfter(referenceDate.atStartOfDay()) } }
        dateTo?.let { referenceDate -> retrievedEvents.stream().allMatch { it.date.isBefore(referenceDate.atTime(LocalTime.MAX)) } }
    }

}