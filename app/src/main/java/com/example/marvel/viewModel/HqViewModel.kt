package com.example.marvel.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.api.ResponseApi
import com.example.marvel.home.HqBusiness
import com.example.marvel.model.Hq
import kotlinx.coroutines.launch

class HqViewModel:ViewModel() {

    private val business =HqBusiness()
    val hqLiveData:MutableLiveData<Hq> = MutableLiveData()
    val erro:MutableLiveData<String> = MutableLiveData()

    fun getHq(){


        viewModelScope.launch {
            when(val response = business.getHq()){
                is ResponseApi.Success ->{

                    hqLiveData.postValue(
                        response.data as? Hq
                    )
                }
                is ResponseApi.Error->{
                    erro.postValue(response.message)

                }
            }


        }

    }
}