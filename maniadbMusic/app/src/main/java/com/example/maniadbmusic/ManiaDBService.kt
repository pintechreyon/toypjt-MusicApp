

import com.example.maniadbmusic.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ManiaDBService {
    @GET("api/search/{search}/?sr=artist&display=10&key=example&v=0.5")
    suspend fun searchArtist(@Path("search", encoded = true) search: String): Response<ApiResponse>
}
