package com.luukitoo.btuhomework2.screens.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luukitoo.btuhomework2.data.Movie
import com.luukitoo.btuhomework2.data.MoviesDataProvider
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    val movies get() = MoviesDataProvider.moviesFlow

    fun toggleFavorite(movie: Movie) = viewModelScope.launch {
        MoviesDataProvider.toggleFavorite(movie)
    }
}