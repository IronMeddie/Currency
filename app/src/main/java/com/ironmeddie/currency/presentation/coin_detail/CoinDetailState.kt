package com.ironmeddie.currency.presentation.coin_detail

import com.ironmeddie.currency.domain.models.CoinDetail

data class CoinDetailState (
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error : String = ""
        )