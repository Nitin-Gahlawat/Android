package com.example.audiofetch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class SongsAdapter(private val parentDir: File, private val items: List<String>) :
    RecyclerView.Adapter<SongsAdapter.MyViewHolder>() {


    lateinit var download: (String,Int) -> File?

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val download: ImageButton = view.findViewById(R.id.download)
        val delete: ImageButton = view.findViewById(R.id.deleteSong)
        val play: ImageButton = view.findViewById(R.id.playsong)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_items, parent, false)
        return MyViewHolder(view)
    }


    fun updateViewItemUpdate(position: Int?, holder: MyViewHolder, title: String) {
        val file: File = parentDir.resolve(title)
        if (file.exists()) {
            holder.delete.visibility = View.VISIBLE
            holder.play.visibility = View.VISIBLE
            holder.download.visibility = View.GONE
        } else {
            holder.download.visibility = View.VISIBLE
            holder.play.visibility = View.GONE
            holder.delete.visibility = View.GONE
        }
        if (position != null)
            notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var curr = items[position]
        var title = curr.split("/").last()
        holder.title.text = title

        val file: File = parentDir.resolve(title)
        if (file.exists()) {
            holder.delete.visibility = View.VISIBLE
            holder.play.visibility = View.VISIBLE
            holder.download.visibility = View.GONE
        } else {
            holder.download.visibility = View.VISIBLE
            holder.play.visibility = View.GONE
            holder.delete.visibility = View.GONE
        }


        holder.delete.setOnClickListener {
            val file: File = parentDir.resolve(title)
            if (file.exists()) {
                val deleted = file.delete()
                if (deleted) {
                    Toast.makeText(
                        holder.delete.context,
                        "The file deleted sucessfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    notifyItemChanged(position)
//                    updateViewItemUpdate(position, holder, title)
                }
            }
        }


        holder.download.setOnClickListener {

            download(curr,position)
            //updateViewItemUpdate(position, holder, title)
        }


        holder.play.setOnClickListener {
            holder.play.context.startActivity(
                Intent(
                    holder.play.context,
                    PlayingSong::class.java
                ).apply {
                    val data = parentDir.resolve(title)
                    putExtra("SongUri", data.absolutePath)

                })
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}
