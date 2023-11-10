package com.obaid.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obaid.mvvm.data.models.responses.Hit
import com.obaid.mvvm.data.models.responses.ImagesDetails
import com.obaid.mvvm.data.repos.implementation.ListingRepo
import com.obaid.mvvm.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val listingRepo: ListingRepo) : ViewModel() {

    private val _responseLiveData = MutableLiveData<NetworkResponse<ImagesDetails>>()
    val responseLiveData: LiveData<NetworkResponse<ImagesDetails>>
        get() = _responseLiveData

    private var dataList = emptyList<Hit>()

    fun fetchImages() {
        viewModelScope.launch {
            val res = listingRepo.fetchImages("flower")
            if (res is NetworkResponse.Success && res.data != null && res.data.hits.isEmpty()) {
                _responseLiveData.value = NetworkResponse.Error("Empty data")
                dataList = emptyList()
            } else {
                dataList = res.data!!.hits
                _responseLiveData.value = res
            }
        }
    }

    fun getItemClickData(position: Int): Hit? {
        return if (dataList.isEmpty() || dataList.size < position) null
        else dataList[position]
    }
}