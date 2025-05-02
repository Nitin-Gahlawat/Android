package com.example.imagepicker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagepicker.databinding.ViewItemsBinding
import kotlin.random.Random

data class ImagesPicked(var imageAddress:Uri,var imageName:String)



class NewDiff() : DiffUtil.ItemCallback<ImagesPicked>() {
    override fun areItemsTheSame(oldItem: ImagesPicked, newItem: ImagesPicked): Boolean {
       return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: ImagesPicked, newItem: ImagesPicked): Boolean {
        return oldItem.imageAddress==newItem.imageAddress
    }
}


class ImagesAdapter(var activity:Activity):
    ListAdapter<ImagesPicked, ImagesAdapter.ViewHolder>(NewDiff()) {
    class ViewHolder(itemView: ViewItemsBinding) : RecyclerView.ViewHolder(itemView.root) {
        var imgView=itemView.imageView
        var textView=itemView.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var li=ViewItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(li)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text= getItem(position).imageName
        holder.imgView.apply {
            setImageURI(getItem(position).imageAddress)
            layoutParams.width=200
            layoutParams.height= Random.nextInt(200,500)
            setOnClickListener{
                var i=Intent(context, DisplayBigImages::class.java)
                i.putExtra("ImageUri",getItem(position).imageAddress.toString())
                activity.startActivity(i)

            }
        }

    }

}