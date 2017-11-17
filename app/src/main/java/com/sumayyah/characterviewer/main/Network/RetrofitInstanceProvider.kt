package com.sumayyah.characterviewer.main.Network

import com.google.gson.GsonBuilder
import com.sumayyah.characterviewer.R.string
import com.sumayyah.characterviewer.main.App
import com.sumayyah.characterviewer.main.Console
import com.sumayyah.characterviewer.main.Model.RelatedTopicTypeAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sahmed014c on 11/17/17.
 */
class RetrofitInstanceProvider(val typeAdapterFactory: RelatedTopicTypeAdapterFactory, val okHttpClient: OkHttpClient) { //TODO compose with dagger modules

    fun getRetrofitInstance(): Retrofit {
        Console.log("RetrofitInstanceProvider", "Getting retrofit instance with okhttp and type adapter factory")

        val gson = GsonBuilder() //TODO separate this out as its own dependency passed in through constructor
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create()

        return Builder()
                .baseUrl(App.context.getString(string.base_api_url)) //TODO Inject context
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }
}