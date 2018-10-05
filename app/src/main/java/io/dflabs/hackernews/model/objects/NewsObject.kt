package io.dflabs.hackernews.model.objects

data class NewsObject(val author: String? = "(Author not available)",
                      val title: String? = "(Title not available)",
                      val description: String? = "(Description not available)",
                      val urlToImage: String?,
                      val url: String?)
