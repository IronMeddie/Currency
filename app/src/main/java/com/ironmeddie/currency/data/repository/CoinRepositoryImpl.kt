package com.ironmeddie.currency.data.repository

import com.ironmeddie.currency.data.remote.CoinApi
import com.ironmeddie.currency.data.remote.dto.CoinDetailDto
import com.ironmeddie.currency.data.remote.dto.CoinDto
import com.ironmeddie.currency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinApi) : CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}