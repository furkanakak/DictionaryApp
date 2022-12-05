package com.furkan.mobiversitechallenge.ui.fragment.search_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.furkan.mobiversitechallenge.R
import com.furkan.mobiversitechallenge.common.extension.gone
import com.furkan.mobiversitechallenge.common.extension.visible
import com.furkan.mobiversitechallenge.data.entity.detail.DetailAdapterModel
import com.furkan.mobiversitechallenge.data.entity.dictionary.DictionaryResponse
import com.furkan.mobiversitechallenge.databinding.SearchDetailFragmentBinding
import com.furkan.mobiversitechallenge.di.networking.Resource
import kotlinx.android.synthetic.main.search_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchDetailFragment : Fragment(){
    private lateinit var binding: SearchDetailFragmentBinding
    private val viewModel by viewModel<SearchDetailViewModel>()
    private val dictionaryAdapter = SearchDetailAdapter()
    private val dataMuseAdapter = DetailDataMuseAdapter()
    private lateinit var queryText : String
    private val nounList = ArrayList<DetailAdapterModel>()
    private val verbList = ArrayList<DetailAdapterModel>()
    private val adjectiveList = ArrayList<DetailAdapterModel>()
    private val allList = ArrayList<DetailAdapterModel>()
    private val filterList = ArrayList<DetailAdapterModel>()
    private var visibilityFilterCount = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SearchDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queryText = SearchDetailFragmentArgs.fromBundle(requireArguments()).queryText
        getDictionary(queryText)
        getDataMuse(queryText)
        listener()

    }

    private fun listener() {
        binding.arrowIv.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
        binding.cardNounContainer.setOnClickListener {
            if(cardNounContainer.strokeColor == resources.getColor(R.color.blue_text_color)){
                cardNounContainer.strokeColor = resources.getColor(R.color.white)
                filterList.removeAll(nounList.toSet())
                dictionaryAdapter.setList(filterList)
                visibilityFilterCount--
            }
            else{
                cardNounContainer.strokeColor = resources.getColor(R.color.blue_text_color)
                filterList.addAll(nounList)
                dictionaryAdapter.setList(filterList)
                visibilityFilterCount++
            }
            checkVisibilityFilterCount()
        }
        binding.cardVerbContainer.setOnClickListener {
            if(cardVerbContainer.strokeColor == resources.getColor(R.color.blue_text_color)){
                cardVerbContainer.strokeColor = resources.getColor(R.color.white)
                filterList.removeAll(verbList.toSet())
                dictionaryAdapter.setList(filterList)
                visibilityFilterCount--
            }
            else{
                cardVerbContainer.strokeColor = resources.getColor(R.color.blue_text_color)
                filterList.addAll(verbList)
                dictionaryAdapter.setList(filterList)
                visibilityFilterCount++
            }
            checkVisibilityFilterCount()
        }

        binding.cardAdjectiveContainer.setOnClickListener {
            if(cardAdjectiveContainer.strokeColor == resources.getColor(R.color.blue_text_color)){
                cardAdjectiveContainer.strokeColor = resources.getColor(R.color.white)
                filterList.removeAll(adjectiveList.toSet())
                dictionaryAdapter.setList(filterList)
                visibilityFilterCount--
            }
            else{
                cardAdjectiveContainer.strokeColor = resources.getColor(R.color.blue_text_color)
                filterList.addAll(adjectiveList)
                dictionaryAdapter.setList(filterList)
                visibilityFilterCount++
            }
            checkVisibilityFilterCount()
        }

        binding.filterIconIv.setOnClickListener {
            binding.filterIconIv.gone()
            filterList.clear()
            visibilityFilterCount = 0
            cardNounContainer.strokeColor = resources.getColor(R.color.white)
            cardVerbContainer.strokeColor = resources.getColor(R.color.white)
            cardAdjectiveContainer.strokeColor = resources.getColor(R.color.white)
            dictionaryAdapter.setList(allList)
            }


    }


    private fun getDataMuse(queryText: String) {
        viewModel.getDataMuse(queryText).observe(viewLifecycleOwner){response ->
            when(response.status){
                Resource.Status.SUCCESS ->{
                    dataMuseAdapter.setList(response.data!!)
                    binding.dataMuseRv.adapter = dataMuseAdapter
                    showUi(true)
                }

                Resource.Status.LOADING -> {
                    showUi(false)
                }
                Resource.Status.ERROR -> {
                    Log.d("getDataMuse",response.message.toString())
                }
            }

        }
    }
   fun checkVisibilityFilterCount(){
        if(visibilityFilterCount == 0){
            dictionaryAdapter.setList(allList)
            binding.filterIconIv.gone()
        }
       else
              binding.filterIconIv.visible()

    }

    private fun getDictionary(queryText: String) {
        viewModel.getDictionary(queryText).observe(viewLifecycleOwner) {response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    showUi(true)
                    binding.dictionaryRv.adapter = dictionaryAdapter
                    response.data?.let {
                        initHeader(it)
                        setAdapterListDictionary(it)
                    }
                }
                Resource.Status.ERROR -> {
                    Log.d("getDictionary", "getDictionary: ${response.message}")
                }
                Resource.Status.LOADING -> {
                    showUi(false)
                }
            }
        }
    }

    private fun showUi(show: Boolean) {
        binding.apply {
        if (show) {
                dictionaryRv.visibility = View.VISIBLE
                headerContainer.visibility = View.VISIBLE
                arrowIv.visibility = View.VISIBLE
                synonymTv.visibility = View.VISIBLE
                dataMuseRv.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE

        } else {
            dictionaryRv.visibility = View.INVISIBLE
            headerContainer.visibility = View.INVISIBLE
            arrowIv.visibility = View.INVISIBLE
            synonymTv.visibility = View.INVISIBLE
            dataMuseRv.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }
    }
    private fun initHeader(data: DictionaryResponse) {
       binding.wordTv.text =  data[0].word.toString()
        binding.phoneticTv.text = data[0].phonetic.toString()
    }

    private fun setAdapterListDictionary(data: DictionaryResponse) {
        if (data[0].meanings!=null){
            for (meaning in data[0].meanings!!){
                if (meaning.partOfSpeech!! == "noun"){
                    meaning.definitions?.forEachIndexed { index, definition ->
                        val model = DetailAdapterModel("Noun", definition?.definition?:"", definition?.example?:"",(index+1).toString()+"-")
                        nounList.add(model)
                    }
                }
                if (meaning.partOfSpeech == "verb"){
                    meaning.definitions?.forEachIndexed { index, definition ->
                        val model = DetailAdapterModel("Verb", definition?.definition?:"", definition?.example?:"",(index+1).toString()+"-")
                        verbList.add(model)
                    }
                }
                if (meaning.partOfSpeech == "adjective"){
                    meaning.definitions?.forEachIndexed { index, definition ->
                        val model = DetailAdapterModel("Adjective", definition?.definition?:"", definition?.example?:"",(index+1).toString()+"-")
                        adjectiveList.add(model)
                    }
                }
            }

            allList.addAll(nounList)
            allList.addAll(verbList)
            allList.addAll(adjectiveList)

            dictionaryAdapter.setList(allList)
            binding.dictionaryRv.adapter = dictionaryAdapter
            }

        }

    }

