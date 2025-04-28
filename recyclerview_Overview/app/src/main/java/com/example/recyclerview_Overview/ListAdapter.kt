package com.example.recyclerview_Overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class ExamDiffCallback : DiffUtil.ItemCallback<ExamClass>() {
    override fun areItemsTheSame(oldItem: ExamClass, newItem: ExamClass): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: ExamClass, newItem: ExamClass): Boolean {
        return true
    }
}


class ListAdapterExam() :

    ListAdapter<ExamClass, ListAdapterExam.ViewHolder>(ExamDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.examitem,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curr = currentList[position]
        holder.bind(curr)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heading: TextView = itemView.findViewById(R.id.heading)
        private val body: TextView = itemView.findViewById(R.id.date)
        private val topic: TextView = itemView.findViewById(R.id.topic)
        fun bind(item:ExamClass){
            heading.text = item.name
            body.text=item.date
            topic.text=item.topic
        }
    }


}


class ListAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycle = findViewById<RecyclerView>(R.id.recyclerview)
        findViewById<TextView>(R.id.textView).text="Exam list by List Adapter"
        var adapter=ListAdapterExam()
        adapter.submitList(ExamList)
        recycle.adapter =adapter


    }
}