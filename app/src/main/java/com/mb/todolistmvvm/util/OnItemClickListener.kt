package com.mb.todolistmvvm.util

import com.mb.todolistmvvm.data.Task

interface OnItemClickListener {
    fun onItemClick(task: Task)

    fun onCheckBoxClick(task: Task, isCompleted : Boolean)
}