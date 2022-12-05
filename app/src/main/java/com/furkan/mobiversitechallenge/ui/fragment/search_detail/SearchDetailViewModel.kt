package com.furkan.mobiversitechallenge.ui.fragment.search_detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furkan.mobiversitechallenge.data.entity.datamuse.DataMuseResponse
import com.furkan.mobiversitechallenge.data.entity.dictionary.DictionaryResponse
import com.furkan.mobiversitechallenge.data.repostory.Repository
import com.furkan.mobiversitechallenge.di.networking.Resource


class SearchDetailViewModel(private val repository: Repository) : ViewModel(){
    fun getDictionary(word : String): LiveData<Resource<DictionaryResponse>> = repository.getDictionary(word)
    fun getDataMuse(word : String): LiveData<Resource<DataMuseResponse>> = repository.getDataMuse(word)
}