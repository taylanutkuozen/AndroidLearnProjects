package com.example.learningcalories.roomdb
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learningcalories.model.Food
@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase :RoomDatabase() {
    abstract fun foodDao():FoodDAO
    companion object { //singleton
        @Volatile
        private var instance:FoodDatabase?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock)
        { /*bir nesne olustugunda arka planda invoke fonksiyonu vardir
        nesne olusturuldugunda instance bak bos ise yeni olustur. null verdik direk synchronized gecerek datarace hatasi cozulecekti*/
            instance?:createDatabase(context).also {
                instance=it
            }
        }
        private fun createDatabase(context:Context)= Room.databaseBuilder(
            context.applicationContext,
            FoodDatabase::class.java,
            "FoodDatabase"
        ).build() /*Buradaki esittir function parantezine () karsilik gelir.*/
    }
}
/*
DataRace=Ayni anda ayni database'de farkli threadlerde calisan veriyi degistiren bir durum olursa
Bir thread okurken digeri degistirmeye calisirsa;
Guncel veri gelmeyebilir vs.
*Bunu engellemek icin sync lock kullanariz.
* */
