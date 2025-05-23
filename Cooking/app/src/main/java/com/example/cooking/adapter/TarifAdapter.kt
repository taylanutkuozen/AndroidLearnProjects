package com.example.cooking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cooking.databinding.RecyclerRowBinding
import com.example.cooking.model.Tarif
import com.example.cooking.view.ListFragment
import com.example.cooking.view.ListFragmentDirections

class TarifAdapter(val tarifListesi:List<Tarif>): RecyclerView.Adapter<TarifAdapter.TarifHolder>() {
    class TarifHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifHolder {
        val recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TarifHolder(recyclerRowBinding)
    }

    override fun getItemCount(): Int {
        return tarifListesi.size
    }

    override fun onBindViewHolder(holder: TarifHolder, position: Int) {
        holder.binding.recyclerViewTextView.text=tarifListesi[position].isim
        holder.itemView.setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToRecipesFragment(informArg ="eski", sentID = tarifListesi[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}