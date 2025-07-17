package com.example.kotlinoop

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
        val utku=Sanatci("Utku",28,"Gitar")
        println(utku.isim)
        utku.isim="Utku OZEN"
        utku.sacRengi="Siyah"
        print(utku.gozRengi)/*Goz rengi-->private set public get ile getirildi.*/
        utku.setSesTonu("kalin","utku")
        utku.getSesTonu()
        utku.turuYazdir()
        println(utku.isim)
        utku.sarkiSoyle()
        val taylan=Sanatci("taylan",28,"Keman")
        taylan.sarkiSoyle()

        val kahraman=Kahraman("supermen","ucmak")
        kahraman.kos()
        //kahraman.muhtesemKahraman()-->kahraman class'i icin muhtesemKahraman yok.
        val muhtesemKahraman=MuhtesemKahraman("batman","yarasaAdam")
        muhtesemKahraman.kos()/*kos() metodu inherit edildi.*/
        muhtesemKahraman.muhtesemKahraman()//bu fonksiyon sadece muhtesemKahraman class'a ozel

        val islem=Islemler()
        println(islem.cikarma(10,2))
        println(islem.cikarma(10,2,3))
        println(islem.cikarma(10,2,3,2))

        val kedi=Hayvan()
        val kopek=Kopek()
        val ornekDizi= arrayOf(kedi,kopek)
        ornekDizi.forEach {
            it.sesCikar()
        }

        //Abstraction
        //Abstract Class, Interface
        //val insan=Insan()-->soyut class'lardan nesne olusmaz.
        utku.test()//Abstract class'tan gelir
        utku.sarkiSoyleFonksiyonu()
        utku.dansEtmeFonksiyonu()
    }
}