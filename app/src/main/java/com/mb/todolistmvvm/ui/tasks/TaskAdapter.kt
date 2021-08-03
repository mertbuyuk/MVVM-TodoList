package com.mb.todolistmvvm.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mb.todolistmvvm.data.Task
import com.mb.todolistmvvm.databinding.ItemTasksBinding
import com.mb.todolistmvvm.util.OnItemClickListener

class TaskAdapter(private val listener: OnItemClickListener) : ListAdapter<Task, TaskAdapter.TasksViewHolder>(DiffCallback()) {

    inner class TasksViewHolder(private val binding: ItemTasksBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                checkbox.setOnClickListener {
                    val position = adapterPosition
                    val task = getItem(position)
                    listener.onCheckBoxClick(task,checkbox.isChecked)
                }

                root.setOnClickListener {
                    val position = adapterPosition
                    val task = getItem(position)
                    listener.onItemClick(task)
                }
            }
        }

        fun bind(task: Task){
            binding.apply {
                checkbox.isChecked = task.check
                txtTask.text = task.task
                txtTask.paint.isStrikeThruText = task.check
                imgImportant.isVisible = task.important
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTasksBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }
}