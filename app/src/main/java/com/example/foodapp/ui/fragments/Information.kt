package com.example.foodapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Information : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_information, container, false)

        val githubFab: FloatingActionButton = view.findViewById(R.id.github_FloatingActionButton)
        val linkedinFab: FloatingActionButton = view.findViewById(R.id.linkedin_FloatingActionButton)
        val instagramFab: FloatingActionButton = view.findViewById(R.id.instagram_FloatingActionButton)


        githubFab.setOnClickListener {
            val githubUrl = R.string.GITHUB_URL.toString()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))

            startActivity(intent)
        }

        linkedinFab.setOnClickListener {
            val linkedinUrl = R.string.LINKEDIN_URL.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl))

            startActivity(intent)
        }

        instagramFab.setOnClickListener {
            val instagramUrl = R.string.INSTAGRAM_URL.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))

            startActivity(intent)
        }

        return view

    }

}