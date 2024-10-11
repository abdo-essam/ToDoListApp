package com.ae.todolistapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ae.todolistapp.R
import com.ae.todolistapp.databinding.FragmentAddTaskBinding
import com.ae.todolistapp.ui.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Set the status bar color
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.white)

        // Inflate the layout with data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false)
        binding.addTaskFragment = this@AddTaskFragment
        binding.addTaskToolbarTitle = "Add Task"


        // Observe ViewModel LiveData for task date and time
        setupObservers()

        return binding.root
    }

    // Set up the observers for task date and time
    private fun setupObservers() {
        viewModel.taskDate.observe(viewLifecycleOwner) { date ->
            binding.taskDate = date
        }

        viewModel.taskTime.observe(viewLifecycleOwner) { time ->
            binding.taskTime = time
        }
    }

    // Navigate back to the previous screen
    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    // Select date using ViewModel's date picker logic
    fun selectDate() {
        viewModel.selectDate(requireActivity().supportFragmentManager)
    }

    // Select time using ViewModel's time picker logic
    fun selectTime() {
        viewModel.selectTime(requireActivity().supportFragmentManager)
    }

    // Create a task with provided title, date, and time
    fun createTask(taskTitle: String, taskDate: String, taskTime: String) {
        if (taskTitle.isNotBlank() && taskDate.isNotBlank() && taskTime.isNotBlank()) {
            viewModel.createTask(taskTitle, taskDate, taskTime)
            onBackPressed() // Navigate back after task creation
        } else {
            // Handle invalid input if needed (e.g., show a toast or error message)
        }
    }

}