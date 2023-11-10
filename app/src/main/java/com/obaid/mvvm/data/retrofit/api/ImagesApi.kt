package com.obaid.mvvm.data.retrofit.api

import com.obaid.mvvm.BuildConfig
import com.obaid.mvvm.data.models.responses.ImagesDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo"
    ): Response<ImagesDetails>
}