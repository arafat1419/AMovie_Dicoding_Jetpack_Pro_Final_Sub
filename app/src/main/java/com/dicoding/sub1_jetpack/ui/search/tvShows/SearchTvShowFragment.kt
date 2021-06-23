package com.dicoding.sub1_jetpack.ui.search.tvShows

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.sub1_jetpack.R
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.databinding.FragmentSearchBinding
import com.dicoding.sub1_jetpack.ui.detail.DetailActivity
import com.dicoding.sub1_jetpack.ui.home.HomeViewModel
import com.dicoding.sub1_jetpack.ui.home.tvshows.TvShowsCallback
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchTvShowFragment : DaggerFragment(), TvShowsCallback {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var searchView: SearchView
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setHasOptionsMenu(true)
    }

    override fun onItemClicked(data: TvEntity) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_ID, data.id)
            it.putExtra(DetailActivity.EXTRA_TYPE, "TvShows")
            it.putExtra(DetailActivity.EXTRA_TITLE, data.title)
            it.putExtra(DetailActivity.EXTRA_SEARCH, true)
            context?.startActivity(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel = ViewModelProvider(
                    this@SearchTvShowFragment,
                    factory
                )[HomeViewModel::class.java]
                viewModel.getSearchTvShows(query).observe(viewLifecycleOwner, {
                    if (it.isNullOrEmpty()) {
                        emptyData(true)
                    } else {
                        emptyData(false)
                    }
                    setRecyclerView()
                    showLoading(false)
                    binding?.rvSearch?.adapter.let { adapter ->
                        when (adapter) {
                            is SearchTvShowsAdapter -> {
                                adapter.setData(it)
                            }
                        }
                    }
                })
                showLoading(true)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                binding?.arc?.visibility = View.GONE
                return true
            }
        })
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

    private fun emptyData(state: Boolean) {
        binding!!.apply {
            if (state) {
                imgEmpty.visibility = View.VISIBLE
                txtEmpty.visibility = View.VISIBLE
            } else {
                imgEmpty.visibility = View.GONE
                txtEmpty.visibility = View.GONE
            }
        }
    }

    private fun setRecyclerView() {
        binding?.rvSearch?.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = SearchTvShowsAdapter(this@SearchTvShowFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}