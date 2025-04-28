package com.example.recyclerview_Overview

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class AdapterExam(context: Activity, private var data: ArrayList<ExamClass>) :
    RecyclerView.Adapter<AdapterExam.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.examitem,parent,false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.heading.text=data[position].name.uppercase()
        holder.date.text=data[position].date
        holder.topic.text=data[position].topic
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var heading: TextView = itemView.findViewById(R.id.heading)
        var date: TextView = itemView.findViewById(R.id.date)
        var topic: TextView = itemView.findViewById(R.id.topic)
    }
}


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.textView).text="Exam list by RecyclerView.Adapter"

        val recycle = findViewById<RecyclerView>(R.id.recyclerview)
        recycle.adapter = AdapterExam(this, ExamList)

    }
}