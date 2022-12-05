package com.furkan.mobiversitechallenge.ui.fragment.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.furkan.mobiversitechallenge.databinding.ItemSearchRvBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val list: ArrayList<String> = ArrayList()
    private lateinit var viewClicked : (String) -> Unit

   inner class SearchViewHolder(val binding: ItemSearchRvBinding) :  RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): SearchViewHolder = SearchViewHolder(ItemSearchRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.apply {
            contentTv.text = list[position]
            container.setOnClickListener {
                viewClicked(list[position])
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setViewClicked(viewClicked :(String) -> Unit){
        this.viewClicked = viewClicked
    }

    override fun getItemCount(): Int = list.size

}


