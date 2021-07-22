package com.mb.todolistmvvm.ui

import androidx.lifecycle.ViewModel
import com.mb.todolistmvvm.data.TasksDao
import androidx.hilt.lifecycle.ViewModelInject

class TasksViewModel @ViewModelInject constructor(
    private val tasksDao: TasksDao
) :ViewModel(){
}