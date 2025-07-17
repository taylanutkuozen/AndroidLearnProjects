package com.example.kotlinoop
/*Polymorphism*/
class Islemler {
    fun cikarma(x:Int, y:Int):Int{
        return  x-y
    }
    fun cikarma(x:Int,y:Int,z:Int):Int{
        return x-(y+z)
    }
    fun cikarma(x:Int,y:Int,z:Int,t:Int):Int{
        return x-(y+z+t)
    }
}