package com.luukitoo.btuhomework2.screens.favorive_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luukitoo.btuhomework2.data.Movie
import com.luukitoo.btuhomework2.data.MoviesDataProvider
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel : ViewModel() {

    val movies get() = MoviesDataProvider.moviesFlow
        .map { moviesList ->
            moviesList.filter { movie ->
                movie.isFavorite
            }
        }

    fun toggleFavorite(movie: Movie) = viewModelScope.launch {
        MoviesDataProvider.toggleFavorite(movie)
    }
}
