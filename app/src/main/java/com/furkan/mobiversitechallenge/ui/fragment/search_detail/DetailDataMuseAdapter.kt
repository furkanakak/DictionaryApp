package com.furkan.mobiversitechallenge.ui.fragment.search_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkan.mobiversitechallenge.data.entity.datamuse.DataMuseResponseItem
import com.furkan.mobiversitechallenge.databinding.ItemDatamuseRvBinding

class DetailDataMuseAdapter : RecyclerView.Adapter<DetailDataMuseAdapter.DataMuseViewHolder>() {
    val list : ArrayList<DataMuseResponseItem> = ArrayList()
    inner class DataMuseViewHolder(val binding: ItemDatamuseRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataMuseViewHolder = DataMuseViewHolder(ItemDatamuseRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DataMuseViewHolder, position: Int) {
        holder.binding.apply {
            wordTv.text = list[position].word
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list : ArrayList<DataMuseResponseItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int{
        return if (list.size > 5){
            5
        } else {
            list.size
        }


        }
    }





