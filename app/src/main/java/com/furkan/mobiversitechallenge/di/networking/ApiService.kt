package com.furkan.mobiversitechallenge.di.networking

import com.furkan.mobiversitechallenge.data.entity.datamuse.DataMuseResponse
import com.furkan.mobiversitechallenge.data.entity.dictionary.DictionaryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
  @GET("api/v2/entries/en/{word}")
 suspend fun getDictionary(@Path("word") word: String): Response<DictionaryResponse>

  @GET("words")
 suspend fun getDataMuse(@Query("rel_syn") word : String): Response<DataMuseResponse>
}