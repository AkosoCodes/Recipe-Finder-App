package com.example.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodapp.R
import com.example.foodapp.ui.fragments.Favorites
import com.example.foodapp.ui.fragments.Information
import com.example.foodapp.ui.fragments.Recipes
import com.example.foodapp.databinding.ActivityMainBinding

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
