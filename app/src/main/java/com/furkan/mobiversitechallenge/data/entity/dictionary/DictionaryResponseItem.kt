package com.furkan.mobiversitechallenge.data.entity.dictionary


import com.google.gson.annotations.SerializedName

data class DictionaryResponseItem(
    @SerializedName("license")
    var license: License?,
    @SerializedName("meanings")
    var meanings: List<Meaning>?,
    @SerializedName("phonetic")
    var phonetic: String?,
    @SerializedName("phonetics")
    var phonetics: List<Phonetic>?,
    @SerializedName("sourceUrls")
    var sourceUrls: List<String>?,
    @SerializedName("word")
    var word: String?
)