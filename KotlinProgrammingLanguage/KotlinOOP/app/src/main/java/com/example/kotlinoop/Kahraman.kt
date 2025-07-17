package com.example.kotlinoop

/*default final-->final class, finalde inherit edilemiyor.
open inherit edilebilir-->open class */
open class Kahraman(var isim:String, val ozelGuc:String) {
    fun kos(){
        println("kahraman kostu")
    }
}