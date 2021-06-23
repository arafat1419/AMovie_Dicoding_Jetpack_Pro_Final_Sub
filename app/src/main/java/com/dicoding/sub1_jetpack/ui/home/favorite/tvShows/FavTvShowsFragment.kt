package com.dicoding.sub1_jetpack.ui.home.favorite.tvShows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.databinding.FragmentFavTvShowsBinding
import com.dicoding.sub1_jetpack.ui.detail.DetailActivity
import com.dicoding.sub1_jetpack.ui.home.favorite.FavoriteViewModel
import com.dicoding.sub1_jetpack.ui.home.tvshows.TvShowsAdapter
import com.dicoding.sub1_jetpack.ui.home.tvshows.TvShowsCallback
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavTvShowsFragment : DaggerFragment(), TvShowsCallback {

    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavTvShowsBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavTvShowsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
            viewModel.getListFavTvShows().observe(viewLifecycleOwner, { tv ->
                if (tv != null) {
                    binding?.rvFavTv?.adapter?.let { adapter ->
                        when (adapter) {
                            is TvShowsAdapter -> {
                                if (tv.isNullOrEmpty()) {
                                    emptyData(true)
                                } else {
                                    adapter.submitList(tv)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    private fun emptyData(state: Boolean) {
        with(binding!!) {
            if (state) {
                rvFavTv.visibility = View.GONE
            } else {
                rvFavTv.visibility = View.VISIBLE
            }
        }
    }

    private fun setRecyclerView() {
        binding?.rvFavTv?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowsAdapter(this@FavTvShowsFragment)
        }
    }

    override fun onItemClicked(data: TvEntity) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_ID, data.id)
            it.putExtra(DetailActivity.EXTRA_TYPE, "TvShows")
            it.putExtra(DetailActivity.EXTRA_TITLE, data.title)
            context?.startActivity(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}