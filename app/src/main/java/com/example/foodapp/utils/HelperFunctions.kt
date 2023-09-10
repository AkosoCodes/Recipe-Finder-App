package com.example.foodapp.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.foodapp.R

class HelperFunctions(private val context: Context) {

    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    } // Checks for internet connectivity


    fun openUrl(urlResource: Int) {
        val url = context.getString(urlResource)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } // Opens the url in the browser

    fun updateVisibilityBasedOnInternetConnection(view: View) {
        val frameLayoutView: FrameLayout? = view.findViewById(R.id.frameLayout)
        val imageView: ImageView? = view.findViewById(R.id.noSignalImageView)
        val textView: TextView? = view.findViewById(R.id.noSignal_TextView)

        if (!isInternetAvailable()) {
            frameLayoutView?.visibility = View.GONE
            imageView?.visibility = View.VISIBLE
            textView?.visibility = View.VISIBLE
        } else {
            frameLayoutView?.visibility = View.VISIBLE
            imageView?.visibility = View.GONE
            textView?.visibility = View.GONE
        }
    } // Updates the visibility of the views based on internet connectivity
}