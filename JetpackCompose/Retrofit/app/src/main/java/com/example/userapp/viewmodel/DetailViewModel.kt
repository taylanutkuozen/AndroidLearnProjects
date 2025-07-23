package com.example.userapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.userapp.model.User
import com.example.userapp.services.UserAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailViewModel:ViewModel() {
    private val BASE_URL="https://raw.githubusercontent.com/"
    val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)
    suspend fun getSingleUser(id:Int):User{
        val user=retrofit.getSingleUser()[id]
        println(user.name)
        return user
    }
}