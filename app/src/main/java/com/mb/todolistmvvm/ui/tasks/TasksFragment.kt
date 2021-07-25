package com.mb.todolistmvvm.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mb.todolistmvvm.R
import com.mb.todolistmvvm.databinding.FragmentTasksBinding
import com.mb.todolistmvvm.util.queryChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private val viewModel : TasksViewModel by viewModels()
    private val taskAdapter  = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTasksBinding.bind(view)
        binding.apply {
            recyclerViewTasks.adapter = taskAdapter
            recyclerViewTasks.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.tasks.observe(viewLifecycleOwner){
            taskAdapter.submitList(it)
        }

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_appbar,menu)

        val searchItem = menu.findItem(R.id.actionSearchItem)
        val searchView = searchItem.actionView as SearchView

        searchView.queryChanged {
            viewModel.search.value = it
        }

    }



}