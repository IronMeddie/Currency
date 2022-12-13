package com.ironmeddie.currency.domain.use_case.get_coins

import com.ironmeddie.currency.common.Resource
import com.ironmeddie.currency.data.remote.dto.toCoin
import com.ironmeddie.currency.domain.models.Coin
import com.ironmeddie.currency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository : CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
             emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Succes<List<Coin>>(coins))
        }catch (e: HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e : IOException){
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check the internet connection"))
        }
    }

}