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
import com.example.moviesearchkotlin.ui.presentation.state.Resource
import com.example.moviesearchkotlin.ui.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MovieListViewModel

    private val itemClick: (MovieItem) -> Unit = {
        // TODO implement click listener
        Log.d("TEST", "<><> item click")
    }

    lateinit var  adapter:  MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_recycler_view.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL ,false)
//            adapter = MovieListAdapter(viewModel.movieListData.value?.data!!, itemClick)
        }

        movie_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        search_button.setOnClickListener {
            Log.d("TEST", "<><><> search clicked")
            viewModel.get(search_text.text.toString())
            hideKeyboard()
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MovieListViewModel::class.java]

        // Observe changes to the list and update the UI when changes occur
        viewModel.movieListData.observe(this, Observer {
            updateMovies(it)
        })
    }

    private fun updateMovies(resource: Resource<List<MovieItem>>?) {
        Log.d("TEST", "<><><> updateMovies + " + resource?.data?.get(0))
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    Log.d("TEST", "<><><> Loading")
                    movie_loading_layout.visible()
                    movie_error_layout.gone()
                }
                ResourceState.SUCCESS -> {
                    Log.d("TEST", "<><><> Success")
                    movie_loading_layout.gone()
                    movie_error_layout.gone()
                }
                ResourceState.ERROR -> {
                    Log.d("TEST", "<><><> Error: " + resource.message)
                    movie_loading_layout.gone()
                    movie_error_layout.visible()
                }
            }
            it.data?.let {
                adapter = MovieListAdapter(it, itemClick)
                movie_recycler_view.adapter = adapter
//                adapter.addItems(it)
            }
            it.message?.let {
//                snackBar.show()
            }
        }
    }
}
