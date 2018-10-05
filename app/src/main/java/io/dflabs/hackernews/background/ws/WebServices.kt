package io.dflabs.hackernews.background.ws

import io.dflabs.hackernews.BuildConfig
import io.dflabs.hackernews.background.ws.definitions.ApiDefinition
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebServices {

    companion object {
        fun api(): ApiDefinition {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiDefinition::class.java)
        }
    }
}
