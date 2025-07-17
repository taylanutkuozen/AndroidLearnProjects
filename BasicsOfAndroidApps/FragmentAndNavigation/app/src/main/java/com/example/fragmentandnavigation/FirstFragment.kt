package com.example.fragmentandnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.fragmentandnavigation.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding:FragmentFirstBinding?=null
    private val binding get()=_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? //LayoutInflater xml ile kodu birbirine bagladigimiz bir alan
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentFirstBinding.inflate(inflater,container,false)
        val view=binding.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText2.setText("U")
        /*Hatali-->binding.editText2.text="Utku"*/
        binding.button.setOnClickListener{
            Next(it)//Buradaki "it" parametresi view gostermek icin kullanilir.
        }
        Toast.makeText(requireContext(),"Hosgeldiniz",Toast.LENGTH_LONG).show()
        /*Activitylerden farkli olarak Fragmentlarda Toast mesaji icin context olarak requireContext() parametre olarak uygulanir*/
    }
    fun Next(view:View)
    {
        val name=binding.editText2.text.toString()
        val action=FirstFragmentDirections.actionFirstFragmentToSecondFragment(name)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}