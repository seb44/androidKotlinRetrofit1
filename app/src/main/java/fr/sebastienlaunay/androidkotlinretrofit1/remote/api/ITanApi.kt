package fr.sebastienlaunay.androidkotlinretrofit1.remote.api

import fr.sebastienlaunay.androidkotlinretrofit1.model.TanStop
import retrofit2.Call
import retrofit2.http.GET

interface ITanApi {

    @GET("ewp/arrets.json")
    fun getTanStops(): Call<List<TanStop>>
}