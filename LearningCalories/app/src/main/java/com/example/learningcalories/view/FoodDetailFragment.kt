package com.example.learningcalories.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.learningcalories.databinding.FragmentFoodDetailBinding
import com.example.learningcalories.util.createplaceholder
import com.example.learningcalories.util.installImage
import com.example.learningcalories.viewmodel.FoodDetailViewModel

class FoodDetailFragment : Fragment() {
    private var _binding:FragmentFoodDetailBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel:FoodDetailViewModel
    var foodId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentFoodDetailBinding.inflate(inflater,container,false)
        val view=binding.root
        return  view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(/*owner*/this)[FoodDetailViewModel::class.java]
        arguments?.let{//it:Bundle
            foodId=FoodDetailFragmentArgs.fromBundle(it).foodId
        }
        viewModel.dataFromRoom(foodId)
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner){//it:Food!
            binding.foodNameDetail.text=it.foodName
            binding.foodCaloryDetail.text=it.foodCalory
            binding.foodProtein.text=it.protein
            binding.foodCarbonhydrat.text=it.carbonhydrat
            binding.foodOil.text=it.fat
            binding.foodImage.installImage(it.gorsel, createplaceholder(requireContext()))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}