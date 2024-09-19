package com.skappcoding.debugtests.roomStateProblem

import java.time.LocalDate
import java.time.LocalTime

data class WorkingDayState(
    val workingDays: List<WorkingDay> = emptyList(),
    val date: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val pauseTime: String = "",
    val fixedHourDayDuration: Int = 0,
    val isFixedHourDay: Boolean = false,
    val isActive: Boolean = true,
    val isUnchanged: Boolean = false,
    val note: String = ""
)
