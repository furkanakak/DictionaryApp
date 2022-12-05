package com.furkan.mobiversitechallenge.data.entity.dictionary


import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("name")
    var name: String?,
    @SerializedName("url")
    var url: String?
)