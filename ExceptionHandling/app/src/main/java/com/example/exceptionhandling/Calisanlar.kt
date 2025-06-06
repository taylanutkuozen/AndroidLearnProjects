package com.example.exceptionhandling

class Calisanlar(val isim:String,maas:Int, var department:String, var yas:Int) {
    private val _maas=maas
    fun maasGoster() {
        println(_maas)
    }
}