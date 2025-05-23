package com.example.cooking.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cooking.model.Tarif

@Database(entities=[Tarif::class],version=1)/*Database annotation zorunludur.
Birden fazla entity olsaydi [Tarif::class, Yemek:class, Restaurant::class vs.] olarak tanimlamak gerekirdi.
version bilgisi-->diyelim ki tarifi yeni kolonlar ekledik yeni bir database yapacaktir. o nedenle version tutmaktadir.*/
abstract class TarifDatabase:RoomDatabase() {
    abstract fun tarifDao():TarifDao
}