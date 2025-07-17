package com.example.middlelevel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        println("-----lambda------")
        //lambda--> anonim fonksiyon
        yazdigimiYazdir("taylan")
        val yazdigiYazdirLambda={verilenString:String -> println(verilenString)}
        yazdigiYazdirLambda("yazdir test")
        val carpmaIslemiLambda={a:Int, b:Int -> a*b}
        val sonuc=carpmaIslemiLambda(3,4)
        println(sonuc)
        val carpmaIkinciVersiyon:(Int,Int) -> Int={a,b->a*b}
        println(carpmaIkinciVersiyon(5,3))
        val benimListem = arrayListOf(1,3,5,7,9,11,13,15,17,19)
        val kucukSayilarListesi=benimListem.filter{num-> num<10} //predicate=filter belirtmek kosuldur.
        for(numara in kucukSayilarListesi){
            println(numara)
        }
        val kucukSayilarYeniListesi=benimListem.filter{
            it<10
        }
        for(numara in kucukSayilarYeniListesi)
        {
            println(numara)
        }
        println("----Map-----")
        val karesiAlinmisSayilar=benimListem.map { num->num*num }
        for (numara in karesiAlinmisSayilar){
            println(numara)
        }
        val kupAlinmisSayilar=benimListem.map{it*it*it}
        for(numara in kupAlinmisSayilar){
            println(numara)
        }
        //Map&Filter
        val mapFilterBirArada=benimListem.filter{it<10}.map{it*it}
        mapFilterBirArada.forEach{println(it)}
        println("-----Map&FilterInClass---------")
        val sanatci1=Sanatci("Taylan",20,"keman")
        val sanatci2=Sanatci("Utku",32,"saz")
        val sanatci3=Sanatci("OZEN",57,"piyano")
        val sanatcilar=arrayListOf<Sanatci>(sanatci1,sanatci2,sanatci3)
        val yirmiBestenBuyukSanatcilarEnstrumanlari = sanatcilar.filter{it.yas>25}.map{it.enstruman}
        yirmiBestenBuyukSanatcilarEnstrumanlari.forEach{println(it)}
        val yirmiBestenKucukSanatcilarIsım=sanatcilar.filter{it.yas<25}.map{it.isim}
        yirmiBestenKucukSanatcilarIsım.forEach{println(it)}
        println("----ScopeFonksiyonlari--------------")
        var benimInteger : Int?=null
        benimInteger?.let{
            println(it)
        }
        val yeniInteger=benimInteger?.let{
            it+1 //Eger benimInteger null degilse
        }?: 0 //Eger benimInteger null ise
        println(yeniInteger)
        benimInteger=5
        val yeniInteger2=benimInteger?.let{
            it+1
        }?:0
        println(yeniInteger2)
        //also=bir sey yaptiktan sonra birde bunu yap. Ornegin filtreleme isinden soru bunu yap demek
        sanatcilar.filter { it.yas>25}.also { it.forEach { println(it.enstruman) } }
    }
    fun yazdigimiYazdir(string:String)
    {
        println(string)
    }
}
/*
Coroutines --> Asenkron kod yazirken, suspend(duratılatabilen) fonksiyonlar,thread pool ele alarak asenkron kod yazmamiza olanak saglar.
* */