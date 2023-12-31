package com.example.foodapp.ui.fragments.recipes.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecipesBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipes_bottom_sheet, container, false)
    }
}