package com.example.jokesapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var context: Activity, private var data:List<Jokes>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val x=LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return ViewHolder(x)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind("Joke ${position+1}",data[position].setup,data[position].punchline)
    }
    class ViewHolder(private var itemView: View): RecyclerView.ViewHolder(itemView) {
        private var jokeNo:TextView=itemView.findViewById(R.id.jokeNo)
        private var setup:TextView=itemView.findViewById(R.id.setup)
        private var punchline:TextView=itemView.findViewById(R.id.punchline)
        fun bind(x:String,y:String,z:String){
            jokeNo.text=x
            setup.text=y
            punchline.text=z
        }
    }

}