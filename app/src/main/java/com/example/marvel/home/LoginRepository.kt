package com.example.marvel.home

import com.example.marvel.model.User

class LoginRepository {
    private val listUser:MutableList<User> = mutableListOf()
    fun saveUser(user: User){
        listUser.add(user)

    }
    fun getUser():MutableList<User>{
        return listUser
    }
}