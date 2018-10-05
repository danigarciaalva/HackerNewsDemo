package io.dflabs.hackernews.presenter.callbacks

import io.dflabs.hackernews.model.objects.NewsObject

interface NewsCallback {

    fun onViewStateChanged(newsResult: NewsResult)
}

sealed class NewsResult {
    object Loading : NewsResult()
    object Error : NewsResult()
    data class Success(val news: ArrayList<NewsObject>) : NewsResult()
}
