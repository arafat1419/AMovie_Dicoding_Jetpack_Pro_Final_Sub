package com.dicoding.sub1_jetpack.di

import android.app.Application
import com.dicoding.sub1_jetpack.data.source.CatalogRepository
import com.dicoding.sub1_jetpack.data.source.local.LocalDataSource
import com.dicoding.sub1_jetpack.data.source.local.room.CatalogDao
import com.dicoding.sub1_jetpack.data.source.local.room.CatalogDb
import com.dicoding.sub1_jetpack.data.source.remote.RemoteDataSource
import com.dicoding.sub1_jetpack.data.source.remote.api.ApiService
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideCatalogDatabase(application: Application): CatalogDb =
            CatalogDb.getInstance(application)

        @Singleton
        @Provides
        fun provideCatalogDao(catalogDb: CatalogDb): CatalogDao =
            catalogDb.dao()

        @Singleton
        @Provides
        fun provideLocalDataSource(catalogDao: CatalogDao): LocalDataSource =
            LocalDataSource(catalogDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogRepository = CatalogRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)

    }
}