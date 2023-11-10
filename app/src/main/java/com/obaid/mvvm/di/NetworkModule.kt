package com.obaid.mvvm.di

import com.obaid.mvvm.data.retrofit.api.ImagesApi
import com.obaid.mvvm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build()

    }

    @Singleton
    @Provides
    fun providesImagesApi(retrofit: Retrofit): ImagesApi {
        return retrofit.create(ImagesApi::class.java)
    }
}