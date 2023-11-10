package com.obaid.mvvm.data.repos.implementation

import com.obaid.mvvm.data.datasources.IRemoteDataSource
import com.obaid.mvvm.data.datasources.ILocalDataSource
import com.obaid.mvvm.data.models.responses.ImagesDetails

import com.obaid.mvvm.data.repos.IListingRepo
import com.obaid.mvvm.utils.NetworkResponse
import javax.inject.Inject

class ListingRepo @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : IListingRepo {

    override suspend fun fetchImages(query: String): NetworkResponse<ImagesDetails> {
        return remoteDataSource.fetchImages(query)
    }
}