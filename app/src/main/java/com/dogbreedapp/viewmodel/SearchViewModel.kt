package com.dogbreedapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogbreedapp.utils.MainRepository
import com.dogbreedapp.utils.Resource
import com.dogbreedapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val breedRepository: MainRepository) : ViewModel() {


    val list: MutableState<UIState> = mutableStateOf(UIState())


    fun getBreedList(query:String)=viewModelScope.launch{
        list.value = UIState(isLoading = true)
        try{
            when(val result = breedRepository.getBreedItems(query)){
                is Resource.Error->{
                    list.value = UIState(error = "Something went wrong")
                }
                is Resource.Success->{
                    result.data?.message.let {
                        list.value = UIState(data = it)
                    }

                }


                else -> {}
            }
        }catch (e:Exception){
            list.value = UIState(error = "Something went wrong")
        }





    }


}