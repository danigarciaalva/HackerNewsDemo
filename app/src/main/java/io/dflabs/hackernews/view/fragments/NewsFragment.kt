package io.dflabs.hackernews.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.dflabs.hackernews.R
import io.dflabs.hackernews.model.objects.NewsObject
import io.dflabs.hackernews.presenter.callbacks.NewsCallback
import io.dflabs.hackernews.presenter.callbacks.NewsResult
import io.dflabs.hackernews.presenter.implementations.NewsPresenter
import io.dflabs.hackernews.view.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), NewsCallback {

    private val newsAdapter = NewsAdapter(clickListener = View.OnClickListener { view ->
        view?.let { view1 ->
            val newsObject = view.tag as? NewsObject
            newsObject?.url?.let {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
            }
        }

    })
    private var newsPresenter = NewsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsPresenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        newsPresenter = NewsPresenter(this)
        newsPresenter.fetchNews()
    }

    override fun onViewStateChanged(newsResult: NewsResult) {
        when (newsResult) {
            is NewsResult.Loading -> {
                Toast.makeText(context, R.string.dialog_loading_news, Toast.LENGTH_LONG).show()
            }
            is NewsResult.Error -> {
                Toast.makeText(context, R.string.dialog_error_loading_news, Toast.LENGTH_LONG).show()
            }
            is NewsResult.Success -> {
                newsAdapter.update(newsResult.news)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        newsPresenter.onDestroy()
    }
}