package com.example.cooking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity/*Bu annotation verilmesi zorunludur.*/
data class Tarif (
    /*data class icin Primary Construct zorunludur.*/
    @ColumnInfo(name="isim")
    var isim:String,
    @ColumnInfo(name="malzeme")
    var malzeme:String?,/*Soru isareti nullable yapmaktadir.*/
    @ColumnInfo(name="gorsel")
    var gorsel:ByteArray /*1'ler ve 0'lar olarak tutulur gorseller*/
    ){
    /*Burasi body alanidir.Ve Primarykey vermeyip otomatik olusmasini isteyecegiz.*/
    @PrimaryKey(autoGenerate = true)
    var id=0
}