package com.example.foodapp.data

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    val remoteDataSrc: RemoteDataSrc
) {

    val remote = remoteDataSrc
}