package com.example.cinema.features.feed.api

import com.example.cinema.core.models.FilmsPayload
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FeedApi {
    @GET("films.json")
    fun loadFilmsListAsync(): Deferred<FilmsPayload>
}