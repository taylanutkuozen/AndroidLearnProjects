package com.example.kotlinoop

class Kopek:Hayvan() {
    fun Havla()
    {
        println("kopek havladi")
        super.sesCikar()//inherit class'a super ile erisiriz.
    }

    override fun sesCikar() {
        println("kopek ses cikardi")
    }
}