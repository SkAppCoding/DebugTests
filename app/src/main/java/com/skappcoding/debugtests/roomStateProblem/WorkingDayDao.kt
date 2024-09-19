package com.skappcoding.debugtests.roomStateProblem

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface WorkingDaysDao {

    @Upsert
    suspend fun upsertWorkingDay(workingDay: WorkingDay)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun initInsertWorkingDay(workingDay: WorkingDay)

    @Delete
    fun deleteWorkingDay(workingDay: WorkingDay)

    @Query("SELECT * FROM workingdays WHERE date = :date")
    fun getWorkingDayByDate(date: LocalDate): Flow<WorkingDay>

    @Query("SELECT * FROM workingdays")
    fun getAllWorkingDays(): Flow<List<WorkingDay>>

}