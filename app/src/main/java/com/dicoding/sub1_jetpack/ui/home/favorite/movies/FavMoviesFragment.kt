package com.dicoding.sub1_jetpack.ui.home.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.databinding.FragmentFavMoviesBinding
import com.dicoding.sub1_jetpack.ui.detail.DetailActivity
import com.dicoding.sub1_jetpack.ui.home.favorite.FavoriteViewModel
import com.dicoding.sub1_jetpack.ui.home.movies.MoviesAdapter
import com.dicoding.sub1_jetpack.ui.home.movies.MoviesCallback
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavMoviesFragment : DaggerFragment(), MoviesCallback {

    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavMoviesBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavMoviesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
            viewModel.getListFavMovies().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    binding?.rvFavMovies?.adapter?.let { adapter ->
                        when (adapter) {
                            is MoviesAdapter -> {
                                if (movie.isNullOrEmpty()) {
                                    emptyData(true)
                                } else {
                                    adapter.submitList(movie)
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
                rvFavMovies.visibility = View.GONE
            } else {
                rvFavMovies.visibility = View.VISIBLE
            }
        }
    }

    private fun setRecyclerView() {
        binding?.rvFavMovies?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(this@FavMoviesFragment)
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_ID, data.id)
            it.putExtra(DetailActivity.EXTRA_TYPE, "Movies")
            it.putExtra(DetailActivity.EXTRA_TITLE, data.title)
            context?.startActivity(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}