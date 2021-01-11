package com.example.marvel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.databinding.ItemHqMainBinding
import com.example.marvel.model.*


class HqAdapter(
    private val list: List<Result>,
    private val itemClick: (Int) -> Unit

) : RecyclerView.Adapter<HqAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHqMainBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], itemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemHqMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hq: Result, itemClick: (Int) -> Unit) = with(binding) {

            titleHqMain.text =
                hq.id.toString() //"#${this@ViewHolder.adapterPosition.toString()}"
            val img = hq.thumbnail.path + "/portrait_medium." + hq.thumbnail.extension
            Glide.with(itemView.context).load(img).into(imgHqMain)
            itemView.setOnClickListener {
                itemClick(this@ViewHolder.adapterPosition)
            }


        }
    }
}
