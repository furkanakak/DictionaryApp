package com.furkan.mobiversitechallenge.data.local

import androidx.lifecycle.MutableLiveData
import com.furkan.mobiversitechallenge.common.constants.Constants
import com.furkan.mobiversitechallenge.data.entity.search.SearchListModel
import com.furkan.mobiversitechallenge.di.local_db.ItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.dsl.module


val LocalDataSourceModule = module {
    single { LocalDataSource(get()) }
}
class LocalDataSource(private val itemDao: ItemDao) {

    var list : MutableLiveData<SearchListModel> = MutableLiveData()

     fun roomList() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            list.postValue(itemDao.getList(Constants.DB_Item_Id))
        }
    }

    suspend fun addList(list: ArrayList<String>) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            itemDao.setList(SearchListModel(Constants.DB_Item_Id,list))
        }

    }

    suspend fun deleteList(list: SearchListModel) {
        itemDao.deleteList(list)
    }

}