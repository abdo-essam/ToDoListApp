package com.ae.todolistapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ae.todolistapp.R
import com.ae.todolistapp.databinding.FragmentHomePageBinding
import com.ae.todolistapp.ui.adapter.TaskAdapter
import com.ae.todolistapp.ui.viewmodel.HomePageViewModel
import com.ae.todolistapp.utils.changePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        binding.homePageFragment = this

        viewModel.taskList.observe(viewLifecycleOwner) {
            val adapter = TaskAdapter(requireContext(), it, viewModel)
            binding.taskAdapter = adapter
        }

        binding.textInputEditTextSearch.addTextChangedListener {
            val queryWord = it.toString()
            viewModel.search(queryWord)
        }

        return binding.root
    }

    fun clickFab(view: View) {
        Navigation.changePage(view, R.id.action_homePageFragment_to_addTaskFragment)
    }


}