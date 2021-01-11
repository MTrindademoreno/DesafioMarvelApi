package com.example.marvel.home

import com.example.marvel.model.User

class LoginBusiness {
    private val repository by lazy {
        LoginRepository()
    }

    fun saveUser(user: User){
       return repository.saveUser(user)
    }
    fun getUser(userinput: User):Boolean{

        val lista=repository.getUser()
        return lista.firstOrNull{ user -> user.email==userinput.email && user.password == userinput.password} != null

    }
}