package com.dicoding.sub1_jetpack.ui.home.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sub1_jetpack.databinding.FragmentFavoriteBinding
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { setViewPager(it) }
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
    }

    private fun setViewPager(context: Context) {
        val sectionsPagerAdapter = SectionPagerAdapter(context, childFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabLayout?.setupWithViewPager(binding!!.viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}