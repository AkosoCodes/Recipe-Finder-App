package com.example.foodapp.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?): NetworkResult<T>(data) // Successful response from the API.
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message) // Unsuccessful response from the API.
    class Loading<T>: NetworkResult<T>() // Loading response from the API.


}