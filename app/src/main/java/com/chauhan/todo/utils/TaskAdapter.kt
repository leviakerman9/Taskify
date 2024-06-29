package com.chauhan.todo.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauhan.todo.databinding.TasklistBinding

class TaskAdapter(private var list: MutableList<Task>,private val taskKeys: MutableList<String>):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    private val TAG="TaskAdapter"
    private var listener:TaskInterface?=null

    fun setListener(listener:TaskInterface){
        this.listener=listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val binding=TasklistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
            with(holder){
                with(list[position]){
                    binding.taskTitle.text=this.title
                    binding.taskDesc.text=this.description

                    itemView.setOnClickListener {
                        listener?.onItemClick(this, taskKeys[position])
                    }

                }


            }
    }

    interface TaskInterface{
        fun onItemClick(task: Task, taskKey: String)

    }

    inner class TaskViewHolder(val binding:TasklistBinding):RecyclerView.ViewHolder(binding.root)


}