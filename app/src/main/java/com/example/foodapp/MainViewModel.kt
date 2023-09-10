package com.example.foodapp

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.Repository
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    var recipesRes: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesRes.value = NetworkResult.Loading()
        if (checkInternetConnectivity()) {
            try {
                val response = repository.remoteDataSrc.getRecipes(queries)
                recipesRes.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                recipesRes.value = NetworkResult.Error("Recipes not found.")
            }
        } else {
            recipesRes.value = NetworkResult.Error("Your device is not connected to the internet.")
        }
    }

    private fun handleFoodRecipesResponse(response: retrofit2.Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Request timed out.")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("Too many requests made the API limited.")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun checkInternetConnectivity(): Boolean {
        val connectivityManager = getApplication<MyApplication>().getSystemService(
            CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }
}