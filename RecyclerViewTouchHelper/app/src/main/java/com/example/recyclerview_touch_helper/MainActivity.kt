package com.example.recyclerview_touch_helper

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerview_touch_helper.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random


data class GmailFormat(
    var image: Int,
    var heading: String,
    var body: String,
    var subbody: String,
    var time: String
)


class MainActivity : AppCompatActivity(), DataAdapters.ItemClickListener {


    private lateinit var binding: ActivityMainBinding


    private fun search(currList: List<GmailFormat>, toMatch: String): List<GmailFormat> {
        var finalList = currList.filter {
            return@filter (it.heading.contains(toMatch, true) || it.body.contains(
                toMatch,
                true
            ) || it.subbody.contains(toMatch, true))
        }
        return finalList
    }


    private fun getList(prelist: ArrayList<GmailFormat>) {
        prelist.addAll(generateRandomData())
    }

    var currentElement = 0
    private fun generateRandomData(): ArrayList<GmailFormat> {
        val random = Random()
        val sampleHeadings = listOf(
            "Meeting Reminder",
            "New Offer",
            "Important Notice",
            "Your Package has Arrived",
            "Newsletter",
            "System Update"
        )
        val sampleBodies = listOf(
            "Here are some details about the update.",
            "Don't miss out on this offer!",
            "Your attention is required immediately.",
            "We have some exciting news!",
            "The latest news from our company.",
            "A quick reminder to check your system."
        )
        val sampleSubBodies = listOf(
            "Urgent action required.",
            "Limited time only.",
            "Important info inside.",
            "Click to find out more.",
            "Hurry, offer ends soon.",
            "Be sure to read the full details."
        )
        val sampleTimes = listOf(
            "5 minutes ago",
            "1 hour ago",
            "Yesterday",
            "2 days ago",
            "3 days ago",
            "Just now"
        )

        var sampleimages = listOf(
            R.drawable.baseline_shopping_cart_24,
            R.drawable.baseline_android_24,
            R.drawable.baseline_send_to_mobile_24,
            R.drawable.baseline_person_24
        )
        val gmailList = ArrayList<GmailFormat>()

        for (i in 1..10) {
            currentElement += 1
            val heading = sampleHeadings[random.nextInt(sampleHeadings.size)]
            val body = sampleBodies[random.nextInt(sampleBodies.size)]
            val subbody = sampleSubBodies[random.nextInt(sampleSubBodies.size)]
//            val time = initial_count+i//sampleTimes[random.nextInt(sampleTimes.size)]
            val img = sampleimages[random.nextInt(sampleimages.size)]
            gmailList.add(
                GmailFormat(
                    image = img,  // You need to ensure this drawable resource exists in your project
                    heading = heading,
                    body = body,
                    subbody = subbody,
                    time = currentElement.toString()
                )
            )

        }
        return gmailList
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = generateRandomData()

        val adapter = DataAdapters()

        adapter.submitList(data)

        binding.RView.adapter = adapter
        adapter.setOnClickListener(this)

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter, data))

        itemTouchHelper.attachToRecyclerView(binding.RView)

        binding.search.doOnTextChanged { text, _, _, _ ->
            adapter.submitList(search(data, text.toString()))
        }

        getList(data)
        adapter.submitList(data)
    }


    private var imageUri: Uri? = null
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->


        if (uri != null) {
            imageUri = uri
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }


    override fun onItemClicked(): Uri? {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
        return imageUri
    }
}