package com.furkan.mobiversitechallenge.ui.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkan.mobiversitechallenge.data.entity.search.SearchListModel
import com.furkan.mobiversitechallenge.data.repostory.Repository
import com.furkan.mobiversitechallenge.ui.fragment.search_detail.SearchDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { SearchViewModel(get()) }
    factory {SearchDetailViewModel(get())}
}

class SearchViewModel(private val repository: Repository) : ViewModel(){
    var list : MutableLiveData<SearchListModel> = repository.list
    fun searchListSetDb(list: ArrayList<String>) {
        repository.searchListSetDb(list)
    }

    fun searchListGetDb() {
        repository.searchListGetDb()
    }
}