package com.example.superheroesapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroesapplication.databinding.ActivityMainBinding
import com.example.superheroesapplication.databinding.ActivityTanitimAktiviteBinding

class TanitimAktivite : AppCompatActivity() {
    private lateinit var binding : ActivityTanitimAktiviteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTanitimAktiviteBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_tanitim_aktivite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*Intent Yoluyla Veri Gonderme
        val intentFromAdapter=intent
        /*intentFromAdapter.getSerializableExtra("secilenKahraman",SuperHeroesClass::class.java) Guncel hali. Kullanmadid, eski hali kullandik asagidadir.*/
        val secilenKahraman=intentFromAdapter.getSerializableExtra("secilenKahraman") as SuperHeroesClass
        binding.imageView.setImageResource(secilenKahraman.picture)
        binding.nameTextView.text=secilenKahraman.Name
        binding.jobTextView.text=secilenKahraman.job*/
         //2.Yol Ile Veri Gonderme
        val secilenKahraman=MySingleton.secilenSuperKahraman
        secilenKahraman?.let {
            binding.imageView.setImageResource(secilenKahraman.picture)
            binding.nameTextView.text=secilenKahraman.Name
            binding.jobTextView.text=secilenKahraman.job
        }
            /*
                binding.imageView.setImageResource(secilenKahraman.picture)
                binding.nameTextView.text=secilenKahraman.Name
                binding.jobTextView.text=secilenKahraman.job
            */

    }
}
/*onClick metodunu RecyclerView kendisine veremeyiz. Hangi ogeye tiklandi ise onu baz almak gerekmektedir. */
/*Singleton tek bir objesi olan bir classtir. Daha once olusturulan bir instance var ise o donecektir, yok ise yeni olusturur
* Tek bir nesnesi olan class ihtiyaci olursa Singleton kullanilir.*/