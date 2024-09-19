package com.skappcoding.debugtests.roomStateProblem

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        WorkingTimeDatabase::class.java,
        "working_time_database.db"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideWorkingDaysDao(database: WorkingTimeDatabase) = database.workingDaysDao()
}
