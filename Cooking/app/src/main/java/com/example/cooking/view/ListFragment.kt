package com.example.cooking.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.cooking.adapter.TarifAdapter
import com.example.cooking.databinding.FragmentListBinding
import com.example.cooking.model.Tarif
import com.example.cooking.roomdb.TarifDao
import com.example.cooking.roomdb.TarifDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListFragment : Fragment() {
    private var _binding:FragmentListBinding?=null
    private val binding get()=_binding!!
    private lateinit var db: TarifDatabase
    private lateinit var tarifDao: TarifDao
    private val mDisposable=CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(),TarifDatabase::class.java,"Tarifler").build()
        tarifDao=db.tarifDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentListBinding.inflate(inflater, container, false)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener{addingNew(it)}
        binding.recipeRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        verileriAl()
    }
    private fun verileriAl(){
        mDisposable.add(
            tarifDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }
    private fun handleResponse(tarifler:List<Tarif>)/*Bu bir get oldugu icin return type verilmelidir.*/{
        val adapter=TarifAdapter(tarifler)
        binding.recipeRecyclerView.adapter=adapter
        /*tarifler.forEach{
            println(it.isim)
            println(it.malzeme)
        }*/
    }
    fun addingNew(view:View){
        val action=ListFragmentDirections.actionListFragmentToRecipesFragment(informArg="yeni",sentID=-1)
        Navigation.findNavController(view).navigate(action)
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }
}