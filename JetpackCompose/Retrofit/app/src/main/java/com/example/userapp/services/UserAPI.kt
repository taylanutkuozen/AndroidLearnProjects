package com.example.userapp.services

import com.example.userapp.model.User
import retrofit2.http.GET

interface UserAPI {
    @GET("atilsamancioglu/UsersJSONPlaceHolder/refs/heads/main/users.json")
    suspend fun getData() :List<User>
    @GET("atilsamancioglu/UsersJSONPlaceHolder/refs/heads/main/users.json")
    suspend fun getSingleUser() :List<User>
}