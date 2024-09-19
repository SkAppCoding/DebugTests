package com.skappcoding.debugtests.roomStateProblem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WorkingDayViewModel @Inject constructor(
    private val repository: WorkingDayRepository
) : ViewModel() {

    companion object{
        private const val MILLS = 5_000L
    }

    val allWorkingDaysState : StateFlow<WorkingDayState> = repository.getAllWorkingDays()
        .map {
            Log.d("allWorkingDayState - .map", it.toString())
            WorkingDayState(it)
        }
        .onEach {
            Log.d("allWorkingDayState - .onEach", it.toString())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(MILLS),
            initialValue = WorkingDayState()
        )

    fun getWorkingDayByDate(date: LocalDate) : StateFlow<WorkingDayState> = repository.getWorkingDayByDate(date)
        .map { WorkingDayState(listOf(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(MILLS),
            initialValue = WorkingDayState()
        )

    fun upsertWorkingDay(workingDay: WorkingDay) {
        viewModelScope.launch {
            repository.upsertWorkingDay(workingDay)
        }
    }

    fun initInsertWorkingDay(workingDay: WorkingDay) {
        viewModelScope.launch {
            repository.initInsertWorkingDay(workingDay)
        }
    }


}