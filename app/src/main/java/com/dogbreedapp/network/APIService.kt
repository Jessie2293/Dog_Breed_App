package com.dogbreedapp.network

import com.dogbreedapp.model.DogBreedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService{

    @GET("api/breed/{breed}/images")
    suspend fun getSearch(
        @Path("breed") query: String
    ) : DogBreedResponse
}