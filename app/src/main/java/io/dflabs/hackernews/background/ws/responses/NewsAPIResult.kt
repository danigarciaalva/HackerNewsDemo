package io.dflabs.hackernews.background.ws.responses

import com.google.gson.annotations.SerializedName
import io.dflabs.hackernews.model.objects.NewsObject

data class NewsAPIResult(val status: String?,
                         @SerializedName("articles")
                         val news: ArrayList<NewsObject> = arrayListOf())
