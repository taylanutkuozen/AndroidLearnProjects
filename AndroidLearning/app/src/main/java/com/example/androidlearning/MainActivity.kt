package com.example.androidlearning

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.example.androidlearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Toast.makeText(this@MainActivity,"Hosgeldiniz",Toast.LENGTH_LONG).show()//this@Activity_ismi=ActivityContext temsil eder.
        /*Toast.LENGTH_LONG=Duration*/
        //println("onCreate çalıştırıldı")
    }
    /*override fun onStart() {
        super.onStart()
        println("onStart çalıştırıldı")
    }
    override fun onResume(){
        super.onResume()
        println("onResume çalıştırıldı")
    }
    override fun onPause(){
        super.onPause()
        println("onPause çalıştırıldı")
    }
    override fun onStop() {
        super.onStop()
        println("onStop çalıştırıldı")
    }
    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy çalıştırıldı.")
    }*/
    fun mainSayfa(view: View)
    {
        val intent=Intent(this@MainActivity,LoginPage::class.java)
        val kullaniciAdi=binding.editText3.text.toString()
        intent.putExtra("isim",kullaniciAdi.toString())
        startActivity(intent)
        /*finish()=bu komut destroy eder manuel olarak*/
        //val kullaniciAdi=binding.editText3.text.toString()
        //binding.textView.text=kullaniciAdi
    }
    fun kaydetClick(view:View)
    {

    }
}/*Neler oluyor, neler calistiriliyor, takip edebilmek icin yapmislardir.
Metotlar cagrilir iken context sorulur, nerede calistiriyorsun anlaminda
Aktivite(Activity) Context--> AktiviteContext icerisinden ulasilabilir
App context--> Genel olarak uygulamanin her yerinden ulasabiliriz.
Hangisini verecegimiz yapilan isleme gore degisebilir:
1)Bazilari, metotlar, gorunumler context sordugunda activity soruyordur cunku calistirilan sey sadece aktiviteden calistiriliyor olabilir.
2)App context bazi durumlarda gecerli olabilir.
Kural:Genelde verebiliyorsak activity context verebiliriz.
*/