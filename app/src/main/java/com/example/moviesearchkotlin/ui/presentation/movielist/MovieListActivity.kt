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
import com.example.moviesearchkotlin.ui.presentation.extensions.visible
import com.example.moviesearchkotlin.ui.presentation.state.Resource
import com.example.moviesearchkotlin.ui.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

//    val viewModel : MovieListViewModel by viewModel()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MovieListViewModel


    private val itemClick: (MovieItem) -> Unit = {
        // TODO implement click listener
        Log.d("TEST", "<><> item click")
//        startActivity(SampleNavigation.postDetails(userId = it.userId, postId = it.postId))
    }

    lateinit var  adapter:  MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MovieListViewModel::class.java]

        // Get ViewModel using MovieListViewModelFactory
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)

        // Make initial API call to get the movie list
        viewModel.get()

        // Observe changes to the list and update the UI when changes occur
        viewModel.movieListData.observe(this, Observer {
            updateMovies(it)
        })
        viewModel.movieListData.observe(this, Observer { updateMovies(it) })

//        list_recycler_view.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
//            adapter = MovieListAdapter(mNicolasCageMovies)
//        }
    }

    private fun updateMovies(resource: Resource<List<MovieItem>>?) {
        Log.d("TEST", "<><><> updateMovies + " + resource?.data?.get(0))
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> movie_loading_layout.visible()
                ResourceState.SUCCESS -> movie_loading_layout.gone()
                ResourceState.ERROR -> {
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
