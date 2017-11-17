package com.sumayyah.characterviewer.main.Network

import com.sumayyah.characterviewer.BuildConfig
import com.sumayyah.characterviewer.main.Model.RelatedTopic
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by sahmed014c on 11/17/17.
 */
interface RetrofitAPIService {

    @GET(BuildConfig.API_PARAMS)
    fun getAllData(): Call<List<RelatedTopic>>
}