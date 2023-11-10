package com.obaid.mvvm.data.models.responses

data class ImagesDetails(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)