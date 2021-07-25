package com.mb.todolistmvvm.ui.tasks

import androidx.lifecycle.ViewModel
import com.mb.todolistmvvm.data.TasksDao
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    tasksDao: TasksDao
) :ViewModel(){
    val search = MutableStateFlow("")
    private val taskFlow = search.flatMapLatest {
        tasksDao.getAllTask(it)
    }
    val tasks = taskFlow.asLiveData()
}