package com.example.taskmanager.maintask.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.maintask.di.TaskTable


class NewDiff:DiffUtil.ItemCallback<TaskTable>(){
    override fun areItemsTheSame(oldItem: TaskTable, newItem: TaskTable): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskTable, newItem: TaskTable): Boolean {
        return oldItem == newItem
    }
}


class TaskDataAdapter () :ListAdapter<TaskTable, TaskDataAdapter.ViewHolder>(NewDiff()) {

    var deleteItem : ((TaskTable) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var heading: CheckBox =itemView.findViewById(R.id.heading)
        var body: TextView = itemView.findViewById(R.id.body)
        var delete:ImageButton=itemView.findViewById(R.id.delete_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li=LayoutInflater.from(parent.context).inflate(R.layout.task_items,parent,false)
        return ViewHolder(li)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currEle=getItem(position)
        holder.body.text = currEle.body
        holder.heading.text = currEle.heading

        holder.heading.setOnClickListener{

            if(holder.heading.isChecked){
                holder.body.paintFlags = holder.body.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                currEle.selected=true
            }else{
                holder.body.paintFlags = holder.body.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG.inv())
                currEle.selected=true
            }
        }
        holder.delete.setOnClickListener{
            deleteItem?.invoke(currEle)
        }

//        val someTextView = findViewById(R.id.some_text_view) as TextView
//        someTextView.setText(someString)
//        someTextView.paintFlags = someTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}