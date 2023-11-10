package com.obaid.mvvm.data.repos

import com.obaid.mvvm.data.models.responses.ImagesDetails
import com.obaid.mvvm.utils.NetworkResponse

interface IListingRepo {
    suspend fun fetchImages(query: String): NetworkResponse<ImagesDetails>
}