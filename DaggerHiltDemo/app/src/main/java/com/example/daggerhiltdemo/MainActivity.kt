package com.example.daggerhiltdemo

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.daggerhiltdemo.dataClass.Spacecraft
import com.example.daggerhiltdemo.di.Retrofit.Endpoints
import com.example.daggerhiltdemo.di.RoomDatabase.User
import com.example.daggerhiltdemo.di.RoomDatabase.UserDao
import com.example.daggerhiltdemo.di.Toast.ToastManagerFactory
import com.example.daggerhiltdemo.di.notification.NotificationManagerFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPref: SharedPreferences

    fun ShredPrefEg() {
        sharedPref.edit().putString("1234", "hello world").apply()
        Toast.makeText(this, sharedPref.getString("1234", "xyz"), Toast.LENGTH_LONG).show()
    }


    @Inject
    lateinit var userDao: UserDao
    fun RoomDatabaseEg() {

        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 1..10)
                userDao.insert(User(0, "$i", "abc${i}@yopmail.com"))

            var listuser = userDao.getAllUsers()
            for (i in listuser) {
                Log.d("dbdata", "${i.email} ${i.username}")
            }

        }
    }


    @Inject
    lateinit var GetData: Endpoints
    fun RetrofitExample() {
        lifecycleScope.launch {
            var x: List<Spacecraft> = GetData.getSpaceData().spacecrafts

            for (i in x) {
                Log.d("getRetrofitData", "${i.name} ${i.id}")
            }
        }
    }


    @Inject
    lateinit var notificationManagerFactory: NotificationManagerFactory

    @Inject
    lateinit var toast: ToastManagerFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ShredPrefEg()
        toast.create().sendToast("Shared Preference done")

        RoomDatabaseEg()

        toast.create().sendToast("Roomdb doen")

        RetrofitExample()

        toast.create().sendToast("Retrofit doen")

        val notificationManager = notificationManagerFactory.create(
            "hello world",
            "this is msg",
            100,
            "new type notification",
            "cool channel "
        )
        notificationManager.sendNotification()


        toast.create().sendToast("Roomdb doen")


    }
}


//Different types of components in andorid dagger hilt
/*
@InstallIn(SingletonComponent::class)
@InstallIn(ActivityComponent::class)
@InstallIn(FragmentComponent::class)
@InstallIn(ViewModelComponent::class)
@InstallIn(ServiceComponent::class)
@InstallIn(ViewComponent::class)



 */