package com.example.exceptionhandling

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var globalX=10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*val sonuc=intOrNullFunction("10")
        val sonuc2=intOrNullFunction("atil")
        println(sonuc)
        println(sonuc2)
        println(globalX)
        ornekFonksiyon2() //Breakpoint
        ornekFonksiyon()*/
        val ornekString="utku"
        //val integerString=ornekString.toInt()
        //LogCat kirmizilar icerisinde hata nedeni yazar=NumberFormatException
        //---------Calisan-------------
        val utku0=Calisanlar("utku0",25000,"mekanik",26)
        val utku1=Calisanlar("utku1",100000,"L2",35)
        val utku2=Calisanlar("utku2",15000,"finans",32)
        val utku3=Calisanlar("utku3",20000,"muhasebe",61)
        val utku4=Calisanlar("utku4",30000,"isletme",24)
        val utku5=Calisanlar("utku5",35000,"kalite",47)
        val utku6=Calisanlar("utku6",40000,"satinalma",36)
        val utku7=Calisanlar("utku7",45000,"insankaynak",25)
        val utku8=Calisanlar("utku8",10000,"L2",28)
        val utku9=Calisanlar("utku9",50000,"otomasyon",54)
        val calisanlar= arrayListOf(utku0,utku1,utku2,utku3,utku4,utku5,utku6,utku7,utku8,utku9)
        val yasKucuk25Isim=calisanlar.filter{it.yas<25}.map{it.isim}
        yasKucuk25Isim.forEach{ println(it) }
        val departmanAndKisi=calisanlar.filter{it.yas>25}.filter{it.department=="L2"}.map { it.maasGoster() }
        departmanAndKisi.forEach{ println(it) }
        println(utku0.isim)
        utku0.maasGoster()
    }
    fun intOrNullFunction(str: String):Int?{
        try{
                val numara=str.toInt()
                return numara
        } catch(e:NumberFormatException){
            e.printStackTrace()
        }catch(e:IOException){
            e.printStackTrace()
        }
        catch(e:Exception) {
            e.printStackTrace()//->Uygulamayi cokertmeden LogCat'e yazacaktir.
        }
        return null
    }
    fun ornekFonksiyon(){
        globalX=20
        println(globalX)
    }
    fun ornekFonksiyon2(){
        globalX=30
        println(globalX)
    }
}
