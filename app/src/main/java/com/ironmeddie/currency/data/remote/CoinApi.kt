package com.ironmeddie.currency.data.remote

import com.ironmeddie.currency.data.remote.dto.CoinDetailDto
import com.ironmeddie.currency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface CoinApi {
//https://api.coinpaprika.com/v1/coins
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>


    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId : String) : CoinDetailDto


}