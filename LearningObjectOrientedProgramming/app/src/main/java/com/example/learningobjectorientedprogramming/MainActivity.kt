package com.example.learningobjectorientedprogramming
//Empty Views Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var sayac=0
    lateinit var benimKahraman:SuperKahraman /*Herhangi bir deger atamadik. lateinit-->Later initialize manasinda. Lateinit yaparken developer olarak hata yapmamiz mumkundur. */
    override fun onCreate(savedInstanceState: Bundle?) {/*onCreate metodu kullanicinin gordugu ekran olusturuldugunda, kullanici gormeden once yapilacak islemler var ise bu metodun icerisine yazilir.*/
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        benimKahraman=SuperKahraman("utku",28,"L2 muhendisi")
        println("onCreate calistirildi")
        birinciFonksiyon()
        birinciFonksiyon()
        birinciFonksiyon()
        ikinciFonksiyon()
        birinciFonksiyon()
        minusProcess(7,5) //Uint--sonuc diye bir sey yok--void
        Adding(10,15) //Int-->return--sonunda degiskene atamak
        val sonuc=minusProcess(5,-7)
        println(sonuc)//kotlin.Uint
        val digerSonuc=Adding(5,7)
        println(digerSonuc)//12
        val superman=SuperKahraman("Clark Kent",30,"Gazeteci")/*Class_Ismi()--> bir nesne olustur anlamina gelmektedir.*/
        println(superman.yas)
        /* Ilk constructorsiz ornek
        val batman=SuperKahraman()
        batman.isim="Bruce Wayne"
        batman.meslek="Holding Sahibi"
        batman.yas=35*/
        //Nullability=
        val kullaniciGirdi="50"
        val kullaniciGirdisiInteger=kullaniciGirdi.toIntOrNull()//toInt() yerine kullanilmasi daha dogru
        if(kullaniciGirdisiInteger!=null)
        {
            println(kullaniciGirdisiInteger*2)
        }
        var benimDouble:Double?=null //Double yanindaki ? ifadesi Nullable oldugunu gostermektedir.
        val kullaniciGirdisiDouble=kullaniciGirdi.toDoubleOrNull()
        kullaniciGirdisiDouble!!.div(2) /*!!--> Null degil eminim demek, yurek yemek, cok beklenen degil*/
        kullaniciGirdisiDouble?.div(2)/*?-->Sonuc null gelebilir anlamina gelir uygulamayi cokertmez*/
        println(kullaniciGirdisiDouble?.div(2)?:20) //elvis operatoru=sol taraf null gelmiyorsa sol taraftaki ifadeyi, null geliyorsa sag taraftaki default ifade kullanilsin
        kullaniciGirdisiDouble?.let{
            println(it*2) //?.let icerisinde bir tane it degiskeni var. Eger ifade null ise yanit vermeyecek, degilse islemi yapacaktir. Uygulamayi cokertmez.
        }
    }
    fun birinciFonksiyon() /*fonksiyondan gelen fun anahtar kelimesi kullanilir.*/
    {
        sayac++
        println("birinci fonksiyon şu kadar calistirildi:${sayac}")
        println("Birinci fonksiyon çalıştırıldı")
    }
    fun ikinciFonksiyon()
    {
        sayac=sayac*2
        println("İkinci fonksiyon calistirildi")
        println(benimKahraman.yas)
    }
    //girdi almak
    fun minusProcess(a:Int,b:Int)
    {
        println("Cikarma sonucu: ${a-b}")
    }
    //cikti vermek --> dondurmek, return
    fun Adding(a:Int,b:Int):Int/*yandaki int cikti type vermektedir*/
    {
        return a+b
    }
}