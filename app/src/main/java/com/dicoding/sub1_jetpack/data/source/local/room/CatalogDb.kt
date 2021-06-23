package com.dicoding.sub1_jetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class CatalogDb : RoomDatabase() {
    abstract fun dao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogDb? = null

        fun getInstance(context: Context): CatalogDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CatalogDb::class.java,
                    "catalog_db"
                ).build()
            }
    }
}