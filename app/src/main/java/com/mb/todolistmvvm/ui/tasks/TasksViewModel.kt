package com.mb.todolistmvvm.ui.tasks

import androidx.lifecycle.ViewModel
import com.mb.todolistmvvm.data.TasksDao
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    tasksDao: TasksDao
) :ViewModel(){
    val search = MutableStateFlow("")

    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
    val hide = MutableStateFlow(false)

    private val taskFlow = combine(
        search,
        sortOrder,
        hide
    ){search,sortOrder,hide ->
        Triple(search,sortOrder,hide)
    }.flatMapLatest {(search,sortOrder,hide)->
        tasksDao.getAllTask(search,sortOrder,hide)
    }
    val tasks = taskFlow.asLiveData()
}

enum class SortOrder{BY_DATE, BY_NAME}