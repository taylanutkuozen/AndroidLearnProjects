package com.example.layoutandviews

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.layoutandviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        enableEdgeToEdge()/*Yukaridaki System UI ile ilgilidir.*/
        /*setContentView(R.layout.activity_main) R-->Resources yani kaynaklari belirtir
        setContentView ile hangi layout bagli olacagi gosterilmektedir.
        findViewById komutu tek tek gorunumleri aradigi icin performansli degildir.
        ViewBinding onerilmektedir.
        */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //val image=findViewById<ImageView>(R.id.imageView)/*<>-->Type sormaktadir.
        //image.setImageResource(R.drawable.b)-->Gorsel degistirm komutu
        //val text1=findViewById<TextView>(R.id.textView)
        //text1.text="Merhaba Kotlin"
        binding.textView.text="Merhaba Utku" /*ViewBinding yontemi ile*/
        /*binding.button.setOnClickListener {
            /*Burada yazilan her komut butona tiklandiginda calisacaktir.*/
            binding.textView.text="Butona tiklandi"
        }*/
    }
    /*
    onClick ile Function Kurallari
    -Button onClick propertysinde ne yaziyorsa o function ismi olmalidir.
    -private olmayacak.
    -Parametre olarak view:View almalidir.
    */
    fun KaydetFunction(view: View)/*View classtir, view parametredir.*/{
            binding.textView.text="Kayit edildi"
    }
    fun IptalFunction(view:View){
            binding.textView.text="İptal edildi"
    }
}