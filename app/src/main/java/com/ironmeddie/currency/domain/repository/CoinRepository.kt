package com.ironmeddie.currency.domain.repository

import com.ironmeddie.currency.data.remote.dto.CoinDetailDto
import com.ironmeddie.currency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}