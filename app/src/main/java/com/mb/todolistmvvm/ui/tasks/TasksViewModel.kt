package com.mb.todolistmvvm.ui.tasks

import androidx.lifecycle.ViewModel
import com.mb.todolistmvvm.data.TasksDao
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    tasksDao: TasksDao
) :ViewModel(){
    val tasks = tasksDao.getAllTask().asLiveData()
}