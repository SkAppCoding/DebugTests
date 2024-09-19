package com.skappcoding.debugtests.roomStateProblem

import java.time.LocalDate
import javax.inject.Inject

class WorkingDayRepository @Inject constructor(
    private val dao: WorkingDaysDao
) {

    suspend fun upsertWorkingDay(workingDay: WorkingDay) = dao.upsertWorkingDay(workingDay)
    suspend fun initInsertWorkingDay(workingDay: WorkingDay) = dao.initInsertWorkingDay(workingDay)
    fun deleteWorkingDay(workingDay: WorkingDay) = dao.deleteWorkingDay(workingDay)
    fun getWorkingDayByDate(date: LocalDate) = dao.getWorkingDayByDate(date)
    fun getAllWorkingDays() = dao.getAllWorkingDays()

}