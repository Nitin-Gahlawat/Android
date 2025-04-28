package com.example.recyclerview_touch_helper


import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_touch_helper.databinding.RviewItemsBinding


class NewDiff() : DiffUtil.ItemCallback<GmailFormat>() {
    override fun areItemsTheSame(oldItem: GmailFormat, newItem: GmailFormat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GmailFormat, newItem: GmailFormat): Boolean {
        return oldItem.heading == newItem.heading
    }
}


class DataAdapters ://(var activity: View.OnClickListener) :
    ListAdapter<GmailFormat, DataAdapters.ViewHolder>(NewDiff()) {

    private lateinit var itemClickListener: ItemClickListener

    fun setOnClickListener(listener:ItemClickListener){
        this.itemClickListener = listener;
    }

    interface ItemClickListener {
        fun onItemClicked(): Uri?
    }


    class ViewHolder(itemView: RviewItemsBinding) : RecyclerView.ViewHolder(itemView.root) {
        var img = itemView.image
        var head = itemView.heading
        var body = itemView.body
        var sbody = itemView.subbody
        var time = itemView.time

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var li = RviewItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(li)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.img.apply {


            setImageResource(currentList[position].image)
            layoutParams.width = 200
            layoutParams.height = 200

            setOnClickListener{

                var imgUri:Uri?=itemClickListener.onItemClicked()
                Log.d("getting",imgUri.toString())
                if(imgUri!=null){
                    setImageURI(imgUri)
                    imgUri=null
                }
                else{
                    Log.d("getting data","OkOkOkOk")
                }

            }

        }
        holder.time.text = currentList[position].time
        holder.head.text = currentList[position].heading
        holder.sbody.text = currentList[position].subbody
        holder.body.text = currentList[position].body
    }
}