package com.example.learningcalories.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.learningcalories.roomdb.FoodDatabase

class PrivateSharedPreferences {

    companion object {
        private val TIME="time"
        private var sharedPreferences:SharedPreferences?=null
        @Volatile private var instance: PrivateSharedPreferences?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock)
        { /*bir nesne olustugunda arka planda invoke fonksiyonu vardir
        nesne olusturuldugunda instance bak bos ise yeni olustur. null verdik direk synchronized gecerek datarace hatasi cozulecekti*/
            instance?: createPrivateSharedPrefences(context).also {
                instance=it
            }
        }
        private fun createPrivateSharedPrefences(context: Context) : PrivateSharedPreferences{
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return PrivateSharedPreferences()
        }
    }
    fun saveTime(time:Long){
        sharedPreferences?.edit()?.putLong(TIME,time)?.apply()
    }
    fun getTime()= sharedPreferences?.getLong(TIME,0)
}