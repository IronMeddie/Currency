package com.ironmeddie.currency.presentation.coin_detail


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.currency.common.Constance
import com.ironmeddie.currency.common.Resource
import com.ironmeddie.currency.domain.use_case.get_coin.GetCoinUseCase
import com.ironmeddie.currency.domain.use_case.get_coins.GetCoinsUseCase
import com.ironmeddie.currency.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDeyailViewModel @Inject constructor(private val getCoinUseCase: GetCoinUseCase,
                                              savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constance.PARAM_COIN_ID)?.let { coinId->
            getCoin(coinId)
        }

    }

    private fun getCoin(coinId: String){
        getCoinUseCase(coinId).onEach { result->
                 when(result){
                     is Resource.Succes -> {
                         _state.value = CoinDetailState(coin = result.data )
                     }
                     is Resource.Error ->{
                         _state.value = CoinDetailState(error = result.message ?: "An unexpected error occured")
                     }
                     is Resource.Loading ->{
                         _state.value = CoinDetailState(isLoading = true)
                     }
                 }
             }.launchIn(viewModelScope)
        }

}