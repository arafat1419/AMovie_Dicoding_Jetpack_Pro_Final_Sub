package com.dicoding.sub1_jetpack.ui.detail

import android.content.Intent
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sub1_jetpack.R
import com.dicoding.sub1_jetpack.data.model.DataTrailer
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity
import com.dicoding.sub1_jetpack.databinding.ContentDetailBinding
import com.dicoding.sub1_jetpack.utils.Helper.setImageWithGlide
import com.dicoding.sub1_jetpack.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var type: String
    private var isSearch = false
    private var link: String? = null

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        type = intent.getStringExtra(EXTRA_TYPE)!!
        val title = intent.getStringExtra(EXTRA_TITLE)
        link = intent.getStringExtra(EXTRA_LINK)
        isSearch = intent.getBooleanExtra(EXTRA_SEARCH, false)

        supportActionBar?.title = title
        idClassification(id, type)
    }

    private fun idClassification(id: Int, type: String) {
        viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]

        if (type == "Movies") {
            if (isSearch) {
                viewModel.getDetailSearchMovie(id).observe(this@DetailActivity, {
                    it?.let {
                        dataDisplay(it, null)
                    }
                })
            } else {
                viewModel.getDetailMovie(id).observe(this@DetailActivity, {
                    it?.let {
                        dataDisplay(it, null)
                    }
                })
            }

            viewModel.getTrailerMovie(id).observe(this@DetailActivity, {
                if (it != null) {
                    linkDisplay(it)
                }
            })
        } else if (type == "TvShows") {
            if (isSearch) {
                viewModel.getDetailSearchTv(id).observe(this@DetailActivity, {
                    it?.let {
                        dataDisplay(null, it)
                    }
                })
            } else {
                viewModel.getDetailTvShows(id).observe(this@DetailActivity, {
                    it?.let {
                        dataDisplay(null, it)
                    }
                })
            }

            viewModel.getTrailerTv(id).observe(this@DetailActivity, {
                if (it != null) {
                    linkDisplay(it)
                }
            })
        }
    }

    private fun linkDisplay(data: DataTrailer) {
        link = data.link
    }

    private fun dataDisplay(movies: MovieEntity?, tvShows: TvEntity?) {
        with(binding) {
            txtTitle.text = movies?.title ?: tvShows?.title
            txtDesc.text = movies?.overview ?: tvShows?.overview
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) txtDesc.justificationMode =
                JUSTIFICATION_MODE_INTER_WORD
            txtDate.text = getString(R.string.year, movies?.releaseDate ?: tvShows?.releaseDate)

            val ratingString =
                movies?.rating?.toString()?.replace(".", "") ?: tvShows?.rating.toString()
                    .replace(".", "")

            if (!isSearch) favFab.visibility = View.VISIBLE

            val statusFavorite = movies?.isFavorite ?: tvShows?.isFavorite
            statusFavorite?.let { status ->
                setFavoriteState(status)
            }

            favFab.setOnClickListener {
                setFavorite(movies, tvShows)
            }

            progressRating.progress = ratingString.toInt()
            txtProgress.text = getString(R.string.rating, ratingString)

            btnTrailer.setOnClickListener {
                if (link == null) {
                    Toast.makeText(
                        this@DetailActivity,
                        "Sorry, Don't have any trailer",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Intent(this@DetailActivity, TrailerActivity::class.java).also {
                        it.putExtra(TrailerActivity.EXTRA_LINK, link)
                        startActivity(it)
                    }
                }
            }

            setImageWithGlide(this@DetailActivity, movies?.poster ?: tvShows?.poster, imgPoster)
            setImageWithGlide(
                this@DetailActivity,
                movies?.imgPreview ?: tvShows?.imgPreview,
                imgPrev
            )
        }
    }

    private fun setFavoriteState(status: Boolean) {
        if (status) {
            binding.favFab.setImageResource(R.drawable.ic_fav_true)
        } else {
            binding.favFab.setImageResource(R.drawable.ic_fav_false)
        }
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvEntity?) {
        if (movie != null) {
            if (movie.isFavorite) {
                showSnackBar("${movie.title} Removed from favorite")
                viewModel.setFavoriteMovie(movie, false)
            } else {
                showSnackBar("${movie.title} Added to favorite")
                viewModel.setFavoriteMovie(movie, true)
            }
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite) {
                    showSnackBar("${tvShow.title} Removed from favorite")
                    viewModel.setFavoriteTvShow(tvShow, false)
                } else {
                    showSnackBar("${tvShow.title} Removed from favorite")
                    viewModel.setFavoriteTvShow(tvShow, true)

                }
            }
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_LINK = "extra_link"
        const val EXTRA_SEARCH = "extra_search"
    }
}