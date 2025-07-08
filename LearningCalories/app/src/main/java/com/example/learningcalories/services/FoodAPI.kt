package com.example.learningcalories.services

import com.example.learningcalories.model.Food
import retrofit2.http.GET

interface FoodAPI {
    //BaseUrl-->raw.githubusercontent.com/
    //EndPoint-->atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getFood():List<Food>   //coroutine icin suspend kullandik, coroutine kapsamina alabilmek icin
}