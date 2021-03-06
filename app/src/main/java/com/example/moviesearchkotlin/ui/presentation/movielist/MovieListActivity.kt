package com.example.moviesearchkotlin.ui.presentation.movielist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.moviesearchkotlin.R
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import com.example.moviesearchkotlin.ui.presentation.extensions.gone
import com.example.moviesearchkotlin.ui.presentation.extensions.hideKeyboard
import com.example.moviesearchkotlin.ui.presentation.extensions.visible
import com.example.moviesearchkotlin.ui.presentation.moviedetail.MovieDetailDialogFragment
import com.example.moviesearchkotlin.ui.presentation.state.Resource
import com.example.moviesearchkotlin.ui.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MovieListViewModel

    private val itemClick: (MovieItem) -> Unit = { movieItem ->
        // TODO implement click listener
        Log.d("TEST", "<><> item click: " + movieItem.movieTitle)
        val fragmentTransaction = supportFragmentManager?.beginTransaction()
        fragmentTransaction?.let { ft ->
            val movieDetailFragment = MovieDetailDialogFragment.newInstance(movieItem)
            movieDetailFragment.show(ft, "dialog")
        }
    }

    var  adapter:  MovieListAdapter = MovieListAdapter(itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        movie_recycler_view.adapter = adapter

        search_button.setOnClickListener {
            viewModel.get(search_text.text.toString())
            hideKeyboard()
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MovieListViewModel::class.java]

        // Observe changes to the list and update the UI when changes occur
        viewModel.movieListData.observe(this, Observer {
            updateMovies(it)
        })

        movie_recycler_view.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL ,false)
        }
    }

    private fun updateMovies(resource: Resource<List<MovieItem>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    movie_recycler_view.gone()
                    movie_loading_layout.visible()
                    movie_error_layout.gone()
                }
                ResourceState.SUCCESS -> {
                    movie_recycler_view.visible()
                    movie_loading_layout.gone()
                    movie_error_layout.gone()
                    it.data?.let { movieList ->
                        adapter.updateMovieList(movieList)
                    }
                }
                ResourceState.ERROR -> {
                    movie_recycler_view.gone()
                    movie_loading_layout.gone()
                    movie_error_layout.visible()
                    it.message?.let {
//                        snackBar.show()
                    }
                }
            }
        }
    }
}
