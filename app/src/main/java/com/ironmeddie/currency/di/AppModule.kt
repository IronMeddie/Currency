package com.ironmeddie.currency.di

import com.ironmeddie.currency.common.Constance.BASE_URL
import com.ironmeddie.currency.data.remote.CoinApi
import com.ironmeddie.currency.data.repository.CoinRepositoryImpl
import com.ironmeddie.currency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


//    @Provides
//    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    @Provides
//    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()

    @Provides
    @Singleton
    fun provideApi(): CoinApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient())
            .build().create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi) : CoinRepository{
        return CoinRepositoryImpl(api)
    }




}