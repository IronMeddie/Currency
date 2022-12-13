package com.ironmeddie.currency.domain.use_case.get_coin

import com.ironmeddie.currency.common.Resource
import com.ironmeddie.currency.data.remote.dto.toCoinDetail
import com.ironmeddie.currency.domain.models.CoinDetail
import com.ironmeddie.currency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository : CoinRepository) {

    operator fun invoke(coinId : String): Flow<Resource<CoinDetail>> = flow {
        try {
             emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Succes<CoinDetail>(coin))
        }catch (e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e : IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check the internet connection"))
        }
    }

}