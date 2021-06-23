package com.dicoding.sub1_jetpack.ui.search.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.databinding.ItemsRowDataBinding
import com.dicoding.sub1_jetpack.ui.home.movies.MoviesCallback
import com.dicoding.sub1_jetpack.utils.Helper

class SearchMoviesAdapter(private val callback: MoviesCallback) :
    RecyclerView.Adapter<SearchMoviesAdapter.ViewHolder>() {

    private val listData = ArrayList<MovieEntity>()

    fun setData(data: List<MovieEntity>?) {
        if (data == null) return
        this.listData.clear()
        this.listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemsRowDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieEntity) {
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
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}