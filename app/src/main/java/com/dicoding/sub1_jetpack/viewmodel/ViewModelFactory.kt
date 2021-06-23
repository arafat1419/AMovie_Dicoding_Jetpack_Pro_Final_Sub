package com.dicoding.sub1_jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.ui.detail.DetailViewModel
import com.dicoding.sub1_jetpack.ui.home.HomeViewModel
import com.dicoding.sub1_jetpack.ui.home.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mCatalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}