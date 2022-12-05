package com.furkan.mobiversitechallenge.data.entity.dictionary


import com.google.gson.annotations.SerializedName

data class Phonetic(
    @SerializedName("audio")
    var audio: String?,
    @SerializedName("license")
    var license: License?,
    @SerializedName("sourceUrl")
    var sourceUrl: String?,
    @SerializedName("text")
    var text: String?
)