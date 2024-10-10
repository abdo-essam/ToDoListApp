package com.ae.todolistapp.ui.viewmodel

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae.todolistapp.repository.TaskRepository
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    private val taskDate = MutableLiveData<String>()
    private val taskTime = MutableLiveData<String>()

    fun selectDate(fragmentManager: FragmentManager) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.show(fragmentManager, "Date")

        datePicker.addOnPositiveButtonClickListener { selection ->
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            taskDate.value = dateFormat.format(selection)
        }
    }

    fun selectTime(fragmentManager: FragmentManager) {
        val timePicker = MaterialTimePicker.Builder()
            .setTitleText("Select Time")
            .setTimeFormat(TimeFormat.CLOCK_24H) // Optionally, get this dynamically from preferences
            .build()

        timePicker.show(fragmentManager, "Time")

        timePicker.addOnPositiveButtonClickListener {
            val formattedTime = String.format("%02d:%02d", timePicker.hour, timePicker.minute)
            taskTime.value = formattedTime
        }
    }

    fun createTask(taskTitle: String, taskDate: String, taskTime: String) {
        // Using viewModelScope to handle coroutine lifecycle
        viewModelScope.launch {
            taskRepository.createTask(taskTitle, taskDate, taskTime)
        }
    }
}
