package com.example.apibasics

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import java.util.zip.Inflater

class MyAdapter(var context:Activity,var productList: List<Product>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item=LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
       return  productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carr=productList[position]
        holder.bind(carr.thumbnail,carr.title,carr.description, carr.rating.toString())
    }
    class ViewHolder(itemViewHolder: View):RecyclerView.ViewHolder(itemViewHolder) {
        var img:ImageView=itemViewHolder.findViewById(R.id.img)
        var heading:TextView=itemViewHolder.findViewById(R.id.heading)
        var desc:TextView=itemViewHolder.findViewById(R.id.desc)
        var rating:TextView=itemViewHolder.findViewById(R.id.rating)
        fun bind(thumbnail:String,heading:String,desc:String,rating:String){
            Picasso.get().load(thumbnail).into(img);
            this.heading.text=heading
            this.desc.text=desc
            this.rating.text="Rating $rating/5"
        }
    }


}