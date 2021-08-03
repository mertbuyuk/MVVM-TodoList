package com.mb.todolistmvvm.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mb.todolistmvvm.data.PreferencesManager
import com.mb.todolistmvvm.data.SortOrder
import com.mb.todolistmvvm.data.Task
import com.mb.todolistmvvm.data.TasksDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksDao: TasksDao,
    private val preferencesManager: PreferencesManager
) :ViewModel(){

    val search = MutableStateFlow("")
    val preferencesFlow = preferencesManager.preferencesFlow

    private val taskFlow = combine(
        search,
        preferencesFlow
    ){search,filteredPrefs->
        Pair(search,filteredPrefs)
    }.flatMapLatest {(search,filteredPrefs)->
        tasksDao.getAllTask(search,filteredPrefs.sortOrder,filteredPrefs.hideCompleted)
    }

    val tasks = taskFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedSelected(hideCompleted : Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onTaskSelect(task: Task) {

    }

    fun onCheckBoxChecked(task: Task, completed: Boolean) = viewModelScope.launch {
        tasksDao.update(task.copy(check = completed))
    }



}

