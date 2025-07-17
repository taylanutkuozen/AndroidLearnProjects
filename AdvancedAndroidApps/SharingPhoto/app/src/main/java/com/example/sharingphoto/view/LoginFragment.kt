package com.example.sharingphoto.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.sharingphoto.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginFragment : Fragment() {
    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener { Register(it) }
        binding.buttonLogin.setOnClickListener { Login(it) }
        val currentUser=auth.currentUser
        if(currentUser!=null){
            val action=LoginFragmentDirections.actionLoginFragmentToFeedFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }
    fun Register(view: View){
        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()
        if(email.isNotEmpty()&&password.isNotEmpty())
        {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    val action=LoginFragmentDirections.actionLoginFragmentToFeedFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }.addOnFailureListener{exception->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
    fun Login(view:View)
    {
        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()
        if(email.isNotEmpty()&&password.isNotEmpty())
        {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener{
                val action=LoginFragmentDirections.actionLoginFragmentToFeedFragment()
                Navigation.findNavController(view).navigate(action)
            }.addOnFailureListener { exception->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}