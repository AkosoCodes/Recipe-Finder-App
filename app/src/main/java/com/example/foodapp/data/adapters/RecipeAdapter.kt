package com.example.foodapp.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.models.Result
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val context: Context,
    private val recipes: List<Result>
): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val fragment = LayoutInflater.from(context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(fragment)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.titleView!!.text = recipe.title
        holder.descriptionView!!.text = recipe.summary
        Picasso.get().load(recipe.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(
        private val view : View,
        val titleView: TextView? = view.findViewById<TextView>(R.id.recipe_Title),
        val descriptionView: TextView? = view.findViewById<TextView>(R.id.recipe_Description),
        val imageView: ImageView? = view.findViewById<ImageView>(R.id.recipe_ImageView)
    ): RecyclerView.ViewHolder(view){


    }
}