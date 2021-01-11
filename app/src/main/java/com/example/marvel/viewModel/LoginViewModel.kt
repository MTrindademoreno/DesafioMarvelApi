package com.example.marvel.viewModel

import androidx.lifecycle.ViewModel
import com.example.marvel.home.LoginBusiness
import com.example.marvel.model.User

class LoginViewModel:ViewModel() {

    private val business = LoginBusiness()
    fun saveUser(user: User) {
        business.saveUser(user)
    }
}