package com.dogbreedapp.utils

import com.dogbreedapp.model.DogBreedResponse
import com.dogbreedapp.network.APIService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: APIService) {


    suspend fun getBreedItems(query: String): Resource<DogBreedResponse> {
        return  try{

            val result = apiService.getSearch(query)
            Resource.Success(result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }

}
