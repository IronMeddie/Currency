package com.ironmeddie.currency.presentation.coin_list

import com.ironmeddie.currency.domain.models.Coin

data class CoinListState (
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error : String = ""
        )