package com.furkan.mobiversitechallenge.data.repostory

import androidx.lifecycle.MutableLiveData
import com.furkan.mobiversitechallenge.data.entity.search.SearchListModel
import com.furkan.mobiversitechallenge.data.local.LocalDataSource
import com.furkan.mobiversitechallenge.data.remote.RemoteDataSource
import com.furkan.mobiversitechallenge.di.networking.performNetworkOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.dsl.module

val RepositoryModule = module {
    factory {Repository(get(), get()) }
}

class Repository(private val localDataSource: LocalDataSource ,private val remoteDataSource: RemoteDataSource) {
    var list : MutableLiveData<SearchListModel> = localDataSource.list
    fun searchListSetDb(list: ArrayList<String>) {
        CoroutineScope(Dispatchers.Main).launch { localDataSource.addList(list) }
    }

    fun searchListGetDb() {
         CoroutineScope(Dispatchers.Main).launch { localDataSource.roomList() }
    }

    fun getDictionary(word : String) = performNetworkOperation {remoteDataSource.getDictionary(word)}
    fun getDataMuse(word : String) = performNetworkOperation {remoteDataSource.getDataMuse(word)}

}