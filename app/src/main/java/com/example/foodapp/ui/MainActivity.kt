package com.example.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.adapters.RecipeAdapter
import com.example.foodapp.data.api.SpoonacularHandler
import com.example.foodapp.ui.fragments.Favorites
import com.example.foodapp.ui.fragments.Information
import com.example.foodapp.ui.fragments.Recipes
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Recipes())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.recipesFragment -> replaceFragment(Recipes())
                R.id.favoritesFragment -> replaceFragment(Favorites())
                R.id.informationFragment -> replaceFragment(Information())

                else -> false
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

}
