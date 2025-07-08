package com.example.learningcalories.services
import com.example.learningcalories.model.Food
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class FoodAPIService {
    private val BASE_URL="https://raw.githubusercontent.com/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//JSON veri donecegi soyledik
        .build()
        .create(FoodAPI::class.java)
    suspend fun getData():List<Food>{
        return api.getFood()
    }
}