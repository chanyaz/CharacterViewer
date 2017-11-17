package com.sumayyah.characterviewer.main.Network

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import com.sumayyah.characterviewer.main.App
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.Response
import java.io.IOException

/**
 * Created by sahmed014c on 11/17/17.
 */
class OkHttpClientProvider() {

    private val MAX_CACHE_SIZE = 10 * 1024 * 1024

    fun getClient(): OkHttpClient {

        val cache = Cache(App.context.cacheDir, MAX_CACHE_SIZE.toLong())

        return Builder()
                .cache(cache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    if (isNetworkAvailable()) {
                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                    } else {
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    }
                    chain.proceed(request)
                }
                .build()
    }


    fun isNetworkAvailable(): Boolean {

        val cm = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting) {
            false
        } else true
    }

    //TODO inject application context
}