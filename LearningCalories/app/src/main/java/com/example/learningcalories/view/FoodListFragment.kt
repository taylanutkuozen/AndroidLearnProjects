package com.example.learningcalories.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningcalories.adapter.FoodRecyclerAdapter
import com.example.learningcalories.databinding.FragmentFoodListBinding
import com.example.learningcalories.services.FoodAPI
import com.example.learningcalories.util.myExtension
import com.example.learningcalories.viewmodel.FoodListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
class FoodListFragment : Fragment() {
    private var _binding:FragmentFoodListBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel:FoodListViewModel
    private val foodRecyclerAdapter = FoodRecyclerAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentFoodListBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel=ViewModelProvider(this)[FoodListViewModel::class.java]
        viewModel.refreshData()
        binding.foodRecyclerView.layoutManager=LinearLayoutManager(context)
        binding.foodRecyclerView.adapter=foodRecyclerAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.foodRecyclerView.visibility=View.GONE
            binding.logMessage.visibility=View.GONE
            binding.loadingFood.visibility=View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing=false
        }
        observeLiveData()
        /* Deneme amacli verileri gorduk.
        val retrofit=Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())//JSON veri donecegi soyledik
            .build()
            .create(FoodAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var foods=retrofit.getFood()
            foods.forEach{
                println(it.foodName)
            }
        }
        Dispatchers.Main(thread)-->User islemleri
        Dispatchers.IO(thread)-->database islemleri,get,post islemleri IO'da
        Dispatchers.Default(thread)-->liste siralama islemleri,CPU ya yoracak isler
        *
        val x=""
        x.myExtension("extensionOrnek")
        */
    }
    private fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner){ //it:List<Food>
            binding.foodRecyclerView.visibility=View.VISIBLE
            foodRecyclerAdapter.updateFoodList(it)
        }
        //hata varsa
        viewModel.foodError.observe(viewLifecycleOwner){//it:Boolean
            if(it){
                binding.logMessage.visibility=View.VISIBLE
                binding.foodRecyclerView.visibility=View.GONE
            }else{
                binding.logMessage.visibility=View.GONE
            }
        }
        viewModel.foodLoading.observe(viewLifecycleOwner){//it:Boolean
            if(it){
                binding.foodRecyclerView.visibility=View.GONE
                binding.logMessage.visibility=View.GONE
                binding.loadingFood.visibility=View.VISIBLE
            }else{
                binding.loadingFood.visibility=View.GONE
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}