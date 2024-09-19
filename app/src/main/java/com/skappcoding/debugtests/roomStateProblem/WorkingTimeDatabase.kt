package com.skappcoding.debugtests.roomStateProblem

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WorkingDay::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class, LocalTimeConverter::class)

abstract class WorkingTimeDatabase : RoomDatabase() {
    abstract fun workingDaysDao(): WorkingDaysDao

}