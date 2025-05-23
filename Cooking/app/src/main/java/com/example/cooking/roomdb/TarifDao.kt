package com.example.cooking.roomdb
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cooking.model.Tarif
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
@Dao/*Bu annotation verilmesi zorunludur.*/
interface TarifDao {
    /*Dao icin once bir interface tanimlanir.*/
    @Query("Select * From Tarif")
    fun getAll() : Flowable<List<Tarif>>/*Flowable RxJava dan gelmektedir.*/
    @Query("Select * from Tarif where id=:id")/*function parametresini :(iki nokta) sonra eklememiz gerekir stringin icerisine*/
    fun findById(id:Int):Flowable<Tarif>
    @Insert
    fun insert(tarif: Tarif) : Completable
    @Delete
    fun delete(tarif: Tarif) : Completable
}