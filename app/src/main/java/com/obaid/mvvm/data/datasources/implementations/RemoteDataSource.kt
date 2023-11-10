package com.obaid.mvvm.data.datasources.implementations

import android.util.Log
import com.obaid.mvvm.data.datasources.IRemoteDataSource
import com.obaid.mvvm.data.models.responses.ImagesDetails
import com.obaid.mvvm.data.retrofit.api.ImagesApi
import com.obaid.mvvm.utils.Constants.TAG
import com.obaid.mvvm.utils.NetworkResponse
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: ImagesApi) : IRemoteDataSource {

    override suspend fun fetchImages(query: String): NetworkResponse<ImagesDetails> {
        return try {
            val result = api.getImages(query = query)
            if (result.isSuccessful && result.body() != null) {
                Log.d(TAG,result.body().toString())
                NetworkResponse.Success(result.body()!!)
            } else if (result.errorBody() != null) {
                NetworkResponse.Error(result.errorBody().toString())
            } else {
                NetworkResponse.Error("Unknown Exception")
            }
        } catch (ex: Exception) {
            if (ex is UnknownHostException) {
                NetworkResponse.Error("No internet")
            } else {
                NetworkResponse.Error(ex.message.toString())
            }
        }
    }
}