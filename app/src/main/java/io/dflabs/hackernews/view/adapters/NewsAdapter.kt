package io.dflabs.hackernews.view.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.dflabs.hackernews.R
import io.dflabs.hackernews.model.objects.NewsObject

class NewsAdapter(var news: ArrayList<NewsObject> = arrayListOf(),
                  val clickListener: View.OnClickListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        return NewsViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return this.news.size
    }

    override fun onBindViewHolder(newsViewHolder: NewsViewHolder, position: Int) {
        val newsObject = this.news[position]
        newsViewHolder.titleTextView.text = newsObject.title
        newsViewHolder.descriptionTextView.text = newsObject.description
        newsObject.urlToImage?.let {
            Glide.with(newsViewHolder.itemView.context)
                    .load(it)
                    .into(newsViewHolder.imageView)
        }
        newsViewHolder.itemView.tag = newsObject
    }

    fun update(news: ArrayList<NewsObject>) {
        this.news = news
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View, clickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.itemNewTitle)
        var descriptionTextView: TextView = itemView.findViewById(R.id.itemNewDescription)
        var imageView: ImageView = itemView.findViewById(R.id.itemNewImageView)

        init {
            itemView.setOnClickListener(clickListener)
        }
    }
}
