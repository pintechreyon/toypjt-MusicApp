package com.example.maniadbmusic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/artist")
    fun searchArtist(@Query("q") query: String): Call<ApiResponse>
}
