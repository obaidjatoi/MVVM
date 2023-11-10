package com.obaid.mvvm.data.datasources

import com.obaid.mvvm.data.models.responses.ImagesDetails
import com.obaid.mvvm.utils.NetworkResponse

interface IRemoteDataSource {
    suspend fun fetchImages(query: String): NetworkResponse<ImagesDetails>
}