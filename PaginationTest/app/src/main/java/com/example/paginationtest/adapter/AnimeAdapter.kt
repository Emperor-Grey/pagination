package com.example.paginationtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.paginationtest.data.AnimeData
import com.example.paginationtest.databinding.ItemLayoutBinding

class AnimeAdapter : PagingDataAdapter<AnimeData, AnimeAdapter.AnimeViewHolder>(AnimeDiffCallback) {


    inner class AnimeViewHolder(private val binding: ItemLayoutBinding) : ViewHolder(binding.root) {
        fun bind(anime: AnimeData) {
            binding.originalTitleTextView.text = anime.attributes.canonicalTitle
            binding.posterImageView.load(anime.attributes.posterImage.original) {
                crossfade(true)
            }
        }
    }


    object AnimeDiffCallback : DiffUtil.ItemCallback<AnimeData>() {
        override fun areItemsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        anime?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(view)
    }
}
