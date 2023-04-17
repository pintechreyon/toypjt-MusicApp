package com.example.maniadbmusic

// MainViewModel.kt
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.maniadb.com/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun searchArtist(query: String) {
        val call = apiService.searchArtist(query)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    // 작업 수행
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // 오류 처리
            }
        })
    }
}
