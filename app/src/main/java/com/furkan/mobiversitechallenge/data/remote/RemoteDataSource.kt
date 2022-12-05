package com.furkan.mobiversitechallenge.data.remote

import com.furkan.mobiversitechallenge.common.constants.Constants
import com.furkan.mobiversitechallenge.di.networking.ApiService
import com.furkan.mobiversitechallenge.di.networking.BaseDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module


val remoteDataSourceModule = module {
    factory { RemoteDataSource(get(named(Constants.Dictionary)),get(named(Constants.DataMuse))) }
}
class RemoteDataSource(private val dictionaryApiService: ApiService,private val museApiService: ApiService) : BaseDataSource(){
    suspend fun getDictionary(word : String) = getResult { dictionaryApiService.getDictionary(word) }
    suspend fun getDataMuse(word : String) = getResult { museApiService.getDataMuse(word) }



}