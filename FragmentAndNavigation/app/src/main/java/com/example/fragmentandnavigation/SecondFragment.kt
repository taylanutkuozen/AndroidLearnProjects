package com.example.fragmentandnavigation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentandnavigation.databinding.FragmentSecondBinding

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//Yukaridaki iki parametreye egitimde gereksiz dendigi icin yorum satiri oldu.
class SecondFragment : Fragment() {
    private var _binding:FragmentSecondBinding?=null
    private val binding get()=_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding=FragmentSecondBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let{
            val name=SecondFragmentArgs.fromBundle(it).userName/*it parametresi süslü parantez icerisinde gorunmeyen bundle temsil etmektedir.*/
        /*Argumani sahip her bir Fragment icin sinif olusturulur(SecondFragmentArgs).*/
            binding.textView2.text=name
        } /*nullable, arguman gelirse null olmaz. Bundle bilgi yumagi anlamina gelmektedir.*/

    }

    /*companion object { companion object singleton gibi davranmaktadir.
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    Yukaridaki kod blogu egitimde gereksiz dendigi icin yorum satiri oldu.*/
}