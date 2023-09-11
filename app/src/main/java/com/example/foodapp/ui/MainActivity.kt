package com.example.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodapp.R
import com.example.foodapp.ui.fragments.favorites.Favorites
import com.example.foodapp.ui.fragments.Information
import com.example.foodapp.ui.fragments.recipes.Recipes
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.utils.HelperFunctions

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helperFunctions = HelperFunctions(this)

        initBottomNavigationView(helperFunctions)
        replaceFragment(Recipes())
    }

    private fun initBottomNavigationView(helperFunctions: HelperFunctions) {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.recipesFragment -> {
                    helperFunctions.updateVisibilityBasedOnInternetConnection(binding.root)
                    replaceFragment(Recipes())
                }
                R.id.favoritesFragment -> {
                    helperFunctions.updateVisibilityBasedOnInternetConnection(binding.root)
                    replaceFragment(Favorites())
                }
                R.id.informationFragment -> {
                    helperFunctions.updateVisibilityBasedOnInternetConnection(binding.root)
                    replaceFragment(Information())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
