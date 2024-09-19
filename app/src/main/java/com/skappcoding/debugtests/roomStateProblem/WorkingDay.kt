package com.skappcoding.debugtests.roomStateProblem

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "WorkingDays")
data class WorkingDay(

    @PrimaryKey(autoGenerate = false)
    val date: LocalDate,

    val startTime: LocalTime,
    val endTime: LocalTime,
    val pauseTime: Int,
    val fixedHourDayDuration: Int,
    val isFixedHourDay: Boolean,
    var isActive: Boolean,
    var isUnchanged : Boolean = true,
    var note: String = ""
)