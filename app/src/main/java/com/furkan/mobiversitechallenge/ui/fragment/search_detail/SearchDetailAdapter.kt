package com.furkan.mobiversitechallenge.ui.fragment.search_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.furkan.mobiversitechallenge.data.entity.detail.DetailAdapterModel
import com.furkan.mobiversitechallenge.databinding.ItemSearchDetailRvBinding

class SearchDetailAdapter : RecyclerView.Adapter<SearchDetailAdapter.SearchDetailViewHolder>() {
    private val list: ArrayList<DetailAdapterModel> = ArrayList()

    inner class SearchDetailViewHolder(val binding: ItemSearchDetailRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): SearchDetailViewHolder = SearchDetailViewHolder(ItemSearchDetailRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchDetailViewHolder, position: Int) {
        holder.binding.apply {
            headerTv.text = list[position].headerText
            positionTv.text = list[position].positionText
            bodyTv.text = list[position].bodyText
            if (list[position].exampleText == "") {
                exampleContainer.visibility = View.GONE
            } else {
                exampleContainer.visibility = View.VISIBLE
                exampleTv.text = list[position].exampleText
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<DetailAdapterModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

}
