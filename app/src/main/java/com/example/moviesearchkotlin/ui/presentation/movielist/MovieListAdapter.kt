package com.example.moviesearchkotlin.ui.presentation.movielist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.example.moviesearchkotlin.ui.domain.model.MovieItem
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviesearchkotlin.R

import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter constructor(
    private val itemClick: (MovieItem) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    private var movieItems : List<MovieItem> = listOf()
    override fun getItemCount(): Int = movieItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(v)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) =
        holder.bind(movieItems[position], itemClick)

    fun updateMovieList(movieItems: List<MovieItem>){
        this.movieItems = movieItems
        notifyDataSetChanged()
    }

    inner class MovieHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieItem: MovieItem, clickListener: (MovieItem) -> Unit) {
            itemView.tv_title.text = movieItem.movieTitle
            itemView.tv_id.text = movieItem.movieId
            Glide.with(itemView.context)
                .load(movieItems[position].moviePosterUrl)
                .into(itemView.iv_logo)
            itemView.setOnClickListener { clickListener(movieItem)}
        }
    }
}
