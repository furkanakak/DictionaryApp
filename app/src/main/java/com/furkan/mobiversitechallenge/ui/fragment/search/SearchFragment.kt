package com.furkan.mobiversitechallenge.ui.fragment.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.furkan.mobiversitechallenge.databinding.SearchFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { SearchFragment()}
}

class SearchFragment : Fragment() {
    lateinit var binding: SearchFragmentBinding
    private val viewModel by viewModel<SearchViewModel>()
    private  var adapter: SearchAdapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding
        listener()
        initAdapter()
    }

    private fun initAdapter() {
        viewModel.searchListGetDb()
        viewModel.list.observe(viewLifecycleOwner) {response ->
            val list: ArrayList<String> = ArrayList()
            if (response != null) {
                list.addAll(response.results)
            }
            adapter.setList(list)
            adapter.setViewClicked(::adapterItemClicked)
            binding.searchRv.adapter = adapter
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun listener() {
        binding.apply {
                searchButton.setOnClickListener {
                    if(searchView.query.toString()!=""){
                        viewModel.searchListGetDb()
                        viewModel.list.observe(viewLifecycleOwner) { response ->
                            val list: ArrayList<String> = ArrayList()
                            if (response != null)
                                list.addAll(response.results)
                                if (list.size == 5)
                                    list.removeAt(list.size-1)
                            list.add(0,searchView.query.toString())
                            viewModel.searchListSetDb(list)
                            adapter.setList(list)
                        }
                        navigate(searchView.query.toString())
                    }
                    else
                        Toast.makeText(context,"Text Cannot be left blank!",Toast.LENGTH_SHORT).show()
                }}}

    private fun adapterItemClicked(text: String) {
        navigate(text)
    }

    fun navigate(queryText : String){
        val action = SearchFragmentDirections.actionSearchFragmentToSearchDetailFragment(queryText)
        findNavController().navigate(action)
    }


    }








