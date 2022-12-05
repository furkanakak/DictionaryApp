package com.furkan.mobiversitechallenge.data.entity.dictionary


import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("antonyms")
    var antonyms: List<String?>?,
    @SerializedName("definitions")
    var definitions: List<Definition?>?,
    @SerializedName("partOfSpeech")
    var partOfSpeech: String?,
    @SerializedName("synonyms")
    var synonyms: List<String?>?
)