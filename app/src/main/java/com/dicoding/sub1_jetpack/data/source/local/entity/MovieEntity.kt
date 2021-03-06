package com.dicoding.sub1_jetpack.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies_entities")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "poster")
    val poster: String? = null,

    @ColumnInfo(name = "imgPrev")
    val imgPreview: String? = null,

    @ColumnInfo(name = "rating")
    val rating: Double = 0.0,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
