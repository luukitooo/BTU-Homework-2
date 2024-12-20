package com.luukitoo.btuhomework2.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luukitoo.btuhomework2.R
import com.luukitoo.btuhomework2.data.Movie
import com.luukitoo.btuhomework2.databinding.ItemMovieBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieItemCallback()) {

    private var onFavoriteClick: (Movie) -> Unit = { }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindMovie(movie: Movie) = with(binding) {
            Glide.with(ivMovie)
                .load(movie.imageUrl)
                .into(ivMovie)
            tvTitle.text = movie.title
            tvScore.text = "IMDB: ${movie.rating}"
            ivFavorites.setOnClickListener { onFavoriteClick(movie) }
            ivFavorites.setImageResource(
                if (movie.isFavorite) {
                    R.drawable.ic_star
                } else {
                    R.drawable.ic_star_outline
                }
            )
        }
    }

    private class MovieItemCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(getItem(position))
    }

    fun setOnFavoriteClickListener(onClick: (Movie) -> Unit) {
        onFavoriteClick = onClick
    }
}
