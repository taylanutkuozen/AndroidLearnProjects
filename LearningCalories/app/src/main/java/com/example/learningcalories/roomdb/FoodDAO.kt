package com.example.learningcalories.roomdb
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.learningcalories.model.Food
@Dao
interface FoodDAO {
    @Insert
    suspend fun insertAllFood(vararg food:Food):List<Long>
    /*vararg=Birden fazla ve istedigimiz sayida arguman verebiliyoruz.
    List<Long> donuyor. Ekledigi besinlerin id'sini long olarak geri veriyor
    Dezavantaji id'leri manuel olarak handle ediyor.
    Birden fazla insert icin kullanabiliriz.*/
    @Query("DELETE FROM food")
    suspend fun deleteAllFood()//Coroutine icin suspend ekledik
    @Query("Select * from food")
    suspend fun getAllFood():List<Food>//Food List donecek
    @Query("SELECT * FROM food WHERE uuid=:foodId")
    suspend fun getFood(foodId:Int):Food //Food donecek
}