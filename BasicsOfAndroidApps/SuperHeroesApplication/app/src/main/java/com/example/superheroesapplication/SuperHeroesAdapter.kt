package com.example.superheroesapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroesapplication.databinding.RecyclerRowBinding

class SuperHeroesAdapter(val superHeroesList:ArrayList<SuperHeroesClass>):RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroesViewHolder>() {
/*ViewHolder bir class bombos bir class. Recycler row icin bir binding ile
 kullanabilmek vardir.Binding ile adapter baglamak icin yardimci classtir. */
    class SuperHeroesViewHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)
        /*itemView istiyor parametre olarak. hangi xml kullanilacak diye onu istemektedir*/
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        /*inflate metodu=Xml ile bir kodu baglamaya calisiriz
        Activity icerisinde layoutinflater Android tarafindan olusturulmus
        MainActivity icerisinde olmadigimiz icin LayoutInflater.from() ile aliriz.
        from() parametre hangi context icerisinden ulasmak istedigimizi gosterir. parent nereye baglandigini gosterdigi icin MainActivity demeye gerek kalmamistir.
        inflate() 2.parametre parent istiyor direk metot parametresi olan parent geciliyor.
        inflate() 3.parametre= parent baglayayim mi false versek bile ilk basta farkli davraniyor, sonunda baglaniyor.*/
        return SuperHeroesViewHolder(binding)
    /*recycler row binding initalize etmek icin,
    ViewHolder bir instance olusturmak icin*/
    }

    override fun getItemCount(): Int {
    /*olusturulan recycler rowdan kac adet yapilmasi ile ilgili soruyor.*/
        return superHeroesList.size /*ArrayList icerisinde kac eleman var ise*/
    }

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        /*islemler bitince ne yapacagiz. tiklaninca bunu, tiklanmaz ise sunu yap gibi*/
        holder.binding.textViewRecyclerView.text=superHeroesList[position].Name
        /*position otomatik olarak parametreden gelir.*/
        holder.itemView.setOnClickListener{ /*Recycler View ogelerine onClick metodu buradan tanimlaya baslanir.
        */
            val intent= Intent(holder.itemView.context,TanitimAktivite::class.java) /*1.parametre context, 2.parametre gidilecek sinif*/
            MySingleton.secilenSuperKahraman=superHeroesList[position]
            //Gunceldir.intent.putExtra("secilenKahraman",superHeroesList[position])/*Kendi classlarimizi Serializable yaparsak yollayabiliyoruz.Serializable=veriye cevirip yollamak anlamina gelmektedir.*/
            holder.itemView.context.startActivity(intent)
        }
    }
}