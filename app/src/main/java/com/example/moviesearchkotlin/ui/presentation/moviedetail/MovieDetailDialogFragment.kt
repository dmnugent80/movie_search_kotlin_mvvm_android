package com.example.moviesearchkotlin.ui.presentation.moviedetail

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.View
import com.example.moviesearchkotlin.R
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*

class MovieDetailDialogFragment : AppCompatDialogFragment() {

    var movie: MovieItem? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        movie = arguments?.getParcelable(BUNDLE_MOVIE)

        val builder = AlertDialog.Builder(activity!!, R.style.MovieDialogTheme)
        val view = View.inflate(activity, R.layout.fragment_movie_detail, null)
        builder.setView(view)
        builder.setPositiveButton(
            getString(R.string.ok)
        ) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        initViews(view)
        return builder.create()
    }

    fun initViews(view: View) {
        movie?.let { m ->
            view.movie_title_txt.text = m.movieTitle
            view.plot.text = m.moviePlot

            context?.let { c ->
                Glide.with(c)
                    .load(m.moviePosterUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.baseline_local_movies_black_48)
                            .fitCenter()
                    )
                    .into(view.poster_iv)
            }
        }
    }

    companion object {
        const val BUNDLE_MOVIE = "BUNDLE_MOVIE"
        fun newInstance(movie: MovieItem): MovieDetailDialogFragment {
            val dialogFragment = MovieDetailDialogFragment()
            val args = Bundle()
            args.putParcelable(BUNDLE_MOVIE, movie)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}