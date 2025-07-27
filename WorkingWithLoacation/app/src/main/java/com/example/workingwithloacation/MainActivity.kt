package com.example.workingwithloacation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.workingwithloacation.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var LOCATION_PERMISSION_REQUEST_CODE = 20


    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                800
            )
            false
        } else {
            true
        }
    }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    private var currentLocation: Location? = null

    private lateinit var binding: ActivityMainBinding


    fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun promptToEnableLocation(context: Context) {
        Log.d("gettingdata", "okokok")
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }


    fun setData(longitude: Double, latitude: Double) {


        var x = Geocoder(this)
        var address: MutableList<Address>? = x.getFromLocation(latitude, longitude, 1)
        binding.longitude.text = "longitude $longitude"
        binding.latitude.text = "latitude $latitude"
        var p: Address? = address?.get(0)
        binding.Address.text = p?.getAddressLine(0)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        isLocationPermissionGranted()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest().apply {

            maxWaitTime = TimeUnit.SECONDS.toMillis(2)
            interval = TimeUnit.SECONDS.toMillis(2)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                Log.d("location", "get sucessfull")
                locationResult.lastLocation.let {
                    currentLocation = locationResult.lastLocation
                    var latitude = currentLocation?.latitude
                    var longitude = currentLocation?.longitude

                    Log.d("location", "$latitude,$longitude")
                    if (latitude != null && longitude != null) {
                        setData(longitude, latitude)
                    }


                }


            }
        }



        binding.start.setOnClickListener {
            Looper.myLooper()?.let { it1 ->
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                )
                    if (isLocationEnabled(this@MainActivity)) {
                        fusedLocationProviderClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            it1
                        )
                    } else {
                        Toast.makeText(this@MainActivity,"Please Enable location First",Toast.LENGTH_SHORT).show()
                        promptToEnableLocation(this@MainActivity)
                    }
            }

        }
        binding.stop.setOnClickListener {
            val removeTask = fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            removeTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "Location Callback removed.")
                } else {
                    Log.d("TAG", "Failed to remove Location Callback.")
                }
            }
        }
    }
}