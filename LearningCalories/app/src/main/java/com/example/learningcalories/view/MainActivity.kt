package com.example.learningcalories.view
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningcalories.R

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
    }
}
/*
MVVM
Model-View-View-Model
Bir mimari yapi. Kodu farkli yapilarla yazilmasina olanak saglar.
Test zorlugu, uygulama sisiyor vs. bu tarz durumlar icin MVVM mimari yapisi kullanilabilir.
BusinessLogic ile UI_Logic arasindaki kopruyu sagliyor.
LiveData kullanacagiz. View'a otomatik bilgi gidecek.
Kac tane fragment, kac tane view varsa, o kadar ayri ayri view model yapmak gerekir.
*/