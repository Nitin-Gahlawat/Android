package com.example.imageshare.ui.mainactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imageshare.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GetImageFragment : BottomSheetDialogFragment() {

    lateinit var getIMageFromGallary:()->Unit
    lateinit var getImageFromCamara:()->Unit


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_get_image, container, false)
        v.findViewById<com.google.android.material.button.MaterialButton>(R.id.gallary).setOnClickListener {
            getIMageFromGallary()
            dismiss()
        }

        v.findViewById<com.google.android.material.button.MaterialButton>(R.id.Camara).setOnClickListener {
            getImageFromCamara()
        }

        return v
    }

}