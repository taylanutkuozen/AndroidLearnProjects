package com.example.superheroesapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var superKahramanListesi:ArrayList<SuperHeroesClass>
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
        val superman=SuperHeroesClass("Superman","Journalist",R.drawable.a1)
        /*Image integer verildi. R.drawable. ifadesinde gorsellerin ifadesi geliyor. Android arka tarafta bu imagelere bir sayi atiyor. Kendi yapiyor.*/
        val batman=SuperHeroesClass("Batman","Boss",R.drawable.a2)
        val ironman=SuperHeroesClass("IronMan","Boss of Business",R.drawable.a3)
        val aquaman=SuperHeroesClass("AquaMan","Fisher",R.drawable.a4)
        superKahramanListesi= arrayListOf(superman,batman,ironman,aquaman)
        val adapter = SuperHeroesAdapter(superKahramanListesi)
        binding.superHeroesRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
        /*Alt alta -->LinearLayoutManager
          Izgara -->GradLayoutManager*/
        binding.superHeroesRecyclerView.adapter=adapter
    }
}