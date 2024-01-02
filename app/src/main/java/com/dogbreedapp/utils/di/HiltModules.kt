package com.dogbreedapp.utils.di

import com.dogbreedapp.network.APIService
import com.dogbreedapp.utils.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideApiService(): APIService {
        return Retrofit.Builder().baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }


    @Provides
    fun provideMainRepository(apiService: APIService): MainRepository {
        return MainRepository(apiService = apiService)
    }


}