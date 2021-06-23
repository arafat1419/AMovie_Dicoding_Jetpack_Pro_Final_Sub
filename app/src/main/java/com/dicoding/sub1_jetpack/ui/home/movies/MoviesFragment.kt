package com.dicoding.sub1_jetpack.ui.home.movies

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.sub1_jetpack.R
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Resource
import com.dicoding.sub1_jetpack.data.source.local.voLocal.Status
import com.dicoding.sub1_jetpack.databinding.FragmentMoviesBinding
import com.dicoding.sub1_jetpack.ui.detail.DetailActivity
import com.dicoding.sub1_jetpack.ui.home.HomeViewModel
import com.dicoding.sub1_jetpack.ui.search.movies.SearchMovieFragment
import com.dicoding.sub1_jetpack.ui.search.movies.SearchMoviesAdapter
import com.dicoding.sub1_jetpack.utils.Sorting.NAME
import com.dicoding.sub1_jetpack.utils.Sorting.RANDOM
import com.dicoding.sub1_jetpack.utils.Sorting.VOTE_BEST
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MoviesFragment : DaggerFragment(), MoviesCallback {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: HomeViewModel
    private var sort: String = VOTE_BEST

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(false)

        showLoading(true)

        activity?.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[HomeViewModel::class.java]
            viewModel.getListMovies(VOTE_BEST).observe(viewLifecycleOwner, moviesObserver)
        }

        binding?.searchFab?.setOnClickListener {
            it.visibility = View.GONE
            val mFragment: Fragment = SearchMovieFragment()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.sort_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_best -> sort = VOTE_BEST
            R.id.action_name -> sort = NAME
            R.id.action_random -> sort = RANDOM
        }

        viewModel.getListMovies(sort).observe(viewLifecycleOwner, moviesObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(data: MovieEntity) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_ID, data.id)
            it.putExtra(DetailActivity.EXTRA_TYPE, "Movies")
            it.putExtra(DetailActivity.EXTRA_TITLE, data.title)
            context?.startActivity(it)
        }
    }

    private val moviesObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    binding?.rvMovies?.adapter?.let { adapter ->
                        when (adapter) {
                            is MoviesAdapter -> {
                                adapter.submitList(movies.data)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    showLoading(true)
                    Toast.makeText(
                        context,
                        "Check your internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding!!.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun setRecyclerView(isSearch: Boolean) {
        binding?.rvMovies?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = if (isSearch) {
                SearchMoviesAdapter(this@MoviesFragment)
            } else {
                MoviesAdapter(this@MoviesFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}