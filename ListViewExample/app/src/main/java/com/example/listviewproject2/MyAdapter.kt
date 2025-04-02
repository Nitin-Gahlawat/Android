package com.example.listviewproject2

import android.app.Activity
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val context: Activity, private val userData: ArrayList<User>):ArrayAdapter<User>(context,R.layout.eachitem,userData) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.eachitem, null);
        val img = view.findViewById<CircleImageView>(R.id.image)
        val heading=view.findViewById<TextView>(R.id.Heading)
        val body=view.findViewById<TextView>(R.id.Body)

        heading.text=userData[position].name
        body.text=userData[position].body
        img.setImageResource(userData[position].imgId)

        return view
    }

}
