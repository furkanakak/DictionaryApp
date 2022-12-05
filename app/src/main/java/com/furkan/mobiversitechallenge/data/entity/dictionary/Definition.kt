package com.furkan.mobiversitechallenge.data.entity.dictionary


import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("antonyms")
    var antonyms: List<String?>?,
    @SerializedName("definition")
    var definition: String?,
    @SerializedName("example")
    var example: String?,
    @SerializedName("synonyms")
    var synonyms: List<Any?>?
)