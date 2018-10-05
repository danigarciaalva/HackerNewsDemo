package io.dflabs.hackernews.background.ws.definitions

import io.dflabs.hackernews.BuildConfig
import io.dflabs.hackernews.background.ws.responses.NewsAPIResult
import retrofit2.Call
import retrofit2.http.GET

interface ApiDefinition {

    @GET("/v2/top-headlines?sources=hacker-news&apiKey=${BuildConfig.API_KEY}")
    fun fetchNews(): Call<NewsAPIResult>
}
