package com.furkan.mobiversitechallenge.data.entity.datamuse


import com.google.gson.annotations.SerializedName

data class DataMuseResponseItem(
    @SerializedName("score")
    var score: Int?,
    @SerializedName("word")
    var word: String?
)