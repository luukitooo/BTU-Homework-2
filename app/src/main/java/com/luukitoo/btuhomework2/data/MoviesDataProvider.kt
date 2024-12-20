package com.luukitoo.btuhomework2.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object MoviesDataProvider {

    private val _moviesFlow = MutableStateFlow(getMovies())
    val moviesFlow get() = _moviesFlow.asStateFlow()

    private fun getMovies() = List(20) {
        Movie(
            title = "Movie $it",
            rating = (1..10).random().toFloat(),
            imageUrl = "https://picsum.photos/200/300?random=$it",
            isFavorite = false
        )
    }

    suspend fun toggleFavorite(movie: Movie) {
        _moviesFlow.emit(
            _moviesFlow.value.map {
                if (it.id == movie.id) it.copy(isFavorite = !it.isFavorite) else it
            }
        )
    }
}
