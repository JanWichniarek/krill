package com.krill.core.schedule

import java.util.*

class ScheduleNotFoundException(scheduleId: UUID) : RuntimeException("Schedule with id: " + scheduleId + "not found")