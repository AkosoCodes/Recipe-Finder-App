package com.example.foodapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.example.foodapp.utils.HelperFunctions
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Information : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_information, container, false)
        val helperFunctions = HelperFunctions(container!!.context)

        val githubFab: FloatingActionButton = view.findViewById(R.id.github_FloatingActionButton)
        val linkedinFab: FloatingActionButton = view.findViewById(R.id.linkedin_FloatingActionButton)
        val instagramFab: FloatingActionButton = view.findViewById(R.id.instagram_FloatingActionButton)
        val apiFab: FloatingActionButton = view.findViewById(R.id.api_FloatingActionButton)

        githubFab.setOnClickListener {
            helperFunctions.openUrl(R.string.GITHUB_URL)
        }

        linkedinFab.setOnClickListener {
            helperFunctions.openUrl(R.string.LINKEDIN_URL)
        }

        instagramFab.setOnClickListener {
            helperFunctions.openUrl(R.string.INSTAGRAM_URL)
        }

        apiFab.setOnClickListener {
            helperFunctions.openUrl(R.string.API_URL)
        }



        return view
    }


}