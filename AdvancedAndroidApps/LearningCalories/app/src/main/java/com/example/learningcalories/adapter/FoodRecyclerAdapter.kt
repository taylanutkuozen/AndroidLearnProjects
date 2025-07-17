package com.example.learningcalories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcalories.databinding.FoodRecyclerRowBinding
import com.example.learningcalories.model.Food
import com.example.learningcalories.util.createplaceholder
import com.example.learningcalories.util.installImage
import com.example.learningcalories.view.FoodListFragmentDirections

class FoodRecyclerAdapter(val foodList:ArrayList<Food>):RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>() {
    class FoodViewHolder(val view:FoodRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
       val recyclerRowBinding:FoodRecyclerRowBinding=FoodRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodViewHolder(recyclerRowBinding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    fun updateFoodList(newFoodList:List<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()//RecyclerView guncellenecektir.
    }
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.view.foodName.text=foodList[position].foodName
        holder.view.foodCalory.text=foodList[position].foodCalory
        holder.itemView.setOnClickListener{
            val action=FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.installImage(foodList[position].gorsel, createplaceholder(holder.itemView.context))
    }
}