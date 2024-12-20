package com.luukitoo.btuhomework2.data

import java.util.UUID

data class Movie(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val rating: Float,
    val imageUrl: String,
    val isFavorite: Boolean
)
