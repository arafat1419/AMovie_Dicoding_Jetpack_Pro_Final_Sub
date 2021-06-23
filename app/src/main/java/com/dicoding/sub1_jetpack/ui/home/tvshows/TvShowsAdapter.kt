package com.dicoding.sub1_jetpack.ui.home.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.databinding.ItemsRowDataBinding
import com.dicoding.sub1_jetpack.utils.Helper

class TvShowsAdapter(private val callback: TvShowsCallback) :
    PagedListAdapter<TvEntity, TvShowsAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemsRowDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvEntity) {
            with(binding) {
                txtTitle.text = data.title
                txtRating.text = data.rating.toString()
                itemView.setOnClickListener {
                    callback.onItemClicked(data)
                }
                Helper.setImageWithGlide(itemView.context, data.poster, imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsDataBinding =
            ItemsRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }
}