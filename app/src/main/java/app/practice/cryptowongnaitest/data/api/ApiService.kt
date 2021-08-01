package app.practice.cryptowongnaitest.data.api

import com.example.data.model.response.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/coins")
    suspend fun searchCoins(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CryptoResponse

    @GET("v1/public/coins")
    suspend fun searchCoinsByPrefix(@Query("prefix") prefix: String): CryptoResponse

    @GET("v1/public/coins")
    suspend fun searchCoinsBySymbols(@Query("symbols") symbols: String): CryptoResponse

    @GET("v1/public/coins")
    suspend fun searchCoinsBySlugs(@Query("slugs") slugs: String): CryptoResponse

    @GET("v1/public/coins")
    suspend fun searchCoinsByIds(@Query("ids") ids: String): CryptoResponse

}