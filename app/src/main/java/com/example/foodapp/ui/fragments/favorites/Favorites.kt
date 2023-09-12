package com.example.foodapp.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.adapters.RecipeAdapter
import com.example.foodapp.data.database.DatabaseHelper

class Favorites : Fragment() {

    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var favoriteDatabaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecyclerView = view.findViewById(R.id.favoritesRecyclerView)
        favoriteDatabaseHelper = DatabaseHelper(requireContext())

        val favorites = favoriteDatabaseHelper.getFavorites()

        adapter = RecipeAdapter(
            requireContext(),
            requireActivity().supportFragmentManager,
            favorites,
            this@Favorites
        )

        favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        favoritesRecyclerView.adapter = adapter

        return view
    }

}
