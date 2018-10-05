package io.dflabs.hackernews.presenter.implementations

import io.dflabs.hackernews.background.ws.WebServices
import io.dflabs.hackernews.background.ws.responses.NewsAPIResult
import io.dflabs.hackernews.presenter.callbacks.NewsCallback
import io.dflabs.hackernews.presenter.callbacks.NewsResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(private val newsCallback: NewsCallback) {
    private var newsCall: Call<NewsAPIResult>? = null

    fun fetchNews() {
        newsCallback.onViewStateChanged(NewsResult.Loading)

        newsCall = WebServices.api().fetchNews()
        newsCall?.enqueue(object : Callback<NewsAPIResult> {
            override fun onFailure(call: Call<NewsAPIResult>, t: Throwable) {
                newsCallback.onViewStateChanged(NewsResult.Error)
            }

            override fun onResponse(call: Call<NewsAPIResult>, response: Response<NewsAPIResult>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        newsCallback.onViewStateChanged(NewsResult.Success(it.news))
                    }

                } else {
                    newsCallback.onViewStateChanged(NewsResult.Error)
                }
            }

        })
    }

    fun onCreate() {

    }

    fun onDestroy() {
        newsCall?.let {
            if (!it.isCanceled && it.isExecuted) {
                it.cancel()
            }
        }
    }
}
