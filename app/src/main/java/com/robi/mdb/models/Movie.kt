package com.robi.mdb.models

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int, // 91
    @SerializedName("total_results")
    val totalResults: Int // 1807
)