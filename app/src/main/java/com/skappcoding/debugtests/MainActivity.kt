package com.skappcoding.debugtests

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.skappcoding.debugtests.roomStateProblem.WorkingDay
import com.skappcoding.debugtests.roomStateProblem.WorkingDayViewModel
import com.skappcoding.debugtests.ui.theme.DebugTestsTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DebugTestsTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content(
    viewModelWorkingDays: WorkingDayViewModel = hiltViewModel()
) {
    val workingDays by viewModelWorkingDays.allWorkingDaysState.collectAsState()
    LaunchedEffect(key1 = workingDays) {
        Log.d("workingDays", workingDays.toString())
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {


            viewModelWorkingDays.initInsertWorkingDay(
                WorkingDay(
                    date = LocalDate.now(),
                    startTime = LocalTime.of(7, 0),
                    pauseTime = 30,
                    endTime = LocalTime.of(16, 30),
                    isActive = true,
                    fixedHourDayDuration = (7.7 * 60).toInt(),
                    isFixedHourDay = false,
                    isUnchanged = true,
                    note = ""
                )
            )

        }) {
            Text(text = "add WorkingDay to database")
        }

        if (workingDays.workingDays.isNotEmpty()) {
            Button(onClick = {
                val workingDay = workingDays.workingDays.first()
                workingDay.isActive = !workingDay.isActive
                viewModelWorkingDays.upsertWorkingDay(workingDay)
            }) {
                Text(text = "update WorkingDay in database")
            }


            Text(text = workingDays.workingDays.first().isActive.toString())
        }

    }
}