package com.obaid.mvvm.di

import com.obaid.mvvm.data.datasources.IRemoteDataSource
import com.obaid.mvvm.data.datasources.ILocalDataSource
import com.obaid.mvvm.data.datasources.implementations.LocalDataSource
import com.obaid.mvvm.data.datasources.implementations.RemoteDataSource
import com.obaid.mvvm.data.repos.IListingRepo
import com.obaid.mvvm.data.repos.implementation.ListingRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): IRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource

    @Singleton
    @Binds
    abstract fun providesImagesRepo(listingRepo: ListingRepo): IListingRepo
}