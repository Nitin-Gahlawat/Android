package com.example.newsapplication
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//I have Created a simple news application showing the use case of android Recycler view write a readme.md in markdown format

class NewAdapter(private val context: Activity, private val data:ArrayList<News>):

    RecyclerView.Adapter<NewAdapter.NewViewHolder>() {
//    to create new View Instance
    //        Run when the Element loads in the memory for first time

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return  NewViewHolder(itemView,context)
    }

    //Runs when the element is displayed on the screen
    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val currItem=data[position]
        holder.heading.text=currItem.heading
        holder.body.text=currItem.body
        holder.images.setImageResource(currItem.img)
        holder.itemView.setOnClickListener{
            val i:Intent=Intent(context,NewsDetails::class.java)
            i.putExtra("heading",data[position].heading)
            i.putExtra("body",data[position].body)
            i.putExtra("img",data[position].img)
            context.startActivity(i)
        }
    }


    //Total element count in the array
    override fun getItemCount(): Int {
        return data.size
    }

    class NewViewHolder(itemView: View,context: Activity) : RecyclerView.ViewHolder(itemView) {
        var heading: TextView =itemView.findViewById<TextView>(R.id.heading)
        var body: TextView =itemView.findViewById<TextView>(R.id.body)
        var images: ImageView =itemView.findViewById<ImageView>(R.id.img)

    }

}


class RecycleViewExample<T> : AppCompatActivity() {
    private var data:ArrayList<News> =NewsData.getData();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rView: RecyclerView =findViewById(R.id.recycleView)
        rView.layoutManager=LinearLayoutManager(this)
        rView.adapter=NewAdapter(this,data)

    }
}