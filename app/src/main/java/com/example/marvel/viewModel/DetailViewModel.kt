package com.example.marvel.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.api.ResponseApi
import com.example.marvel.home.DetailBusiness
import com.example.marvel.model.Hq
import com.example.marvel.model.Result
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {
private val business = DetailBusiness()
    val comicLiveData:MutableLiveData<Hq> = MutableLiveData()
    val expandGo:MutableLiveData<Boolean> = MutableLiveData()
    var finishLiveData:MutableLiveData<Boolean> = MutableLiveData()
    val fragmentLiveData:MutableLiveData<Fragment> = MutableLiveData()
    val error:MutableLiveData<String> = MutableLiveData()



    fun getComic(id: Long) {
        viewModelScope.launch {
            when(val response = business.getComic(id)){
                is ResponseApi.Success->{
                    comicLiveData.postValue(
                        response.data as? Hq
                    )
                }
                is ResponseApi.Error->{
                    error.postValue(
                        response.message
                    )

                }
            }
        }
    }

    fun goExpand(){
        expandGo.postValue(true)

    }

    fun finish() {
        finishLiveData.postValue(false)

    }

    fun backFragment(fragment:Fragment) {
        fragmentLiveData.postValue(fragment)


    }


}