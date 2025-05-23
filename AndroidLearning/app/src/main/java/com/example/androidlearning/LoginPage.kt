package com.example.androidlearning

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlearning.databinding.ActivityLoginPageBinding
import com.example.androidlearning.databinding.ActivityMainBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginPageBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Toast.makeText(this, "Hosgeldiniz", Toast.LENGTH_LONG).show();

        //Toast.makeText(this@LoginPage,"Hosgeldiniz",Toast.LENGTH_LONG).show()
        /*val mainIntent=intent
        val yollananIsim=mainIntent.getStringExtra("isim")
        binding.textView3.text=yollananIsim
        fun birinciAktiviteyiAc(view:View)
        {
            //context=Android kodlarda ne oluyor, nerede ne calisiyor, diye gelistirilen bir yapi
            val intent = Intent(this,MainActivity::class.java)
            /*Bir aktivitiden digerine gecerken Intent kullaniriz. Farkli yapilar acmaya olanak saglar. Kullanicinin galerisine gider, ayarlari acabilir.*/
            startActivity(intent)//Uygulamayi bir daha cagirdigimizda hataya dustu.
            /*onCreate bir daha calistirildigi icin MainActivity deki sayfa sifirlanir. onDestroy calistirildi.*/
            /*Yasam dongusune dikkat etmez isek sistem dikkat eder.*/

        }*/
    }
}