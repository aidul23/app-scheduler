package xit.zubrein.demo.ui.scheduler

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import xit.zubrein.demo.model.ModelSchedule
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SchedulerViewModel
@Inject
constructor(private val schedulerRepository: SchedulerRepository) : ViewModel() {

    fun insert(context: Context, schedule: ModelSchedule) {
        viewModelScope.launch {
            val isExists = schedulerRepository.availableData(schedule.scheduleTimeInWord)
            if (!isExists) {
                schedulerRepository.insert(schedule)
            } else {
                Toast.makeText(context, "This schedule time is already taken", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun delete(schedule: ModelSchedule) {
        viewModelScope.launch {
            schedulerRepository.deleteSchedule(schedule)
        }
    }

    fun update(schedule: ModelSchedule) {
        viewModelScope.launch {
            schedulerRepository.updateSchedule(schedule)
        }
    }

}