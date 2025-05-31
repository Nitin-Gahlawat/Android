package com.example.serviceandbroadcast.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

//Create a readme for my project showcasing the use of both service and workManager in markdown format

class AirplaneModeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        if (isAirplaneModeEnabled) {
            Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
        }
    }
}


class NetworkChanged : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action) {
            val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo

            if (activeNetwork != null && activeNetwork.isConnected) {
                Toast.makeText(context, "Device is connected to the internet", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Device is not connected to the internet", Toast.LENGTH_LONG).show()
            }
        }

    }
}