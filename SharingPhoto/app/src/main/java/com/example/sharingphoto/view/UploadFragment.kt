package com.example.sharingphoto.view
import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.sharingphoto.databinding.FragmentUploadBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

//BasicSecurityRules Firebase
class UploadFragment : Fragment() {
    private var _binding : FragmentUploadBinding?=null
    private val binding get()=_binding!!
    private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher:ActivityResultLauncher<String>
    var selectedImage : Uri?=null
    var selectedBitmap : Bitmap? =null
    private lateinit var auth : FirebaseAuth
    private lateinit var storage : FirebaseStorage
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth
        storage=FirebaseStorage.getInstance("gs://sharingphotos-3a7ca.firebasestorage.app")
        db=Firebase.firestore
        registerLaunchers()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding=FragmentUploadBinding.inflate(inflater,container,false)
            val view=binding.root
            return  view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.uploadPicture.setOnClickListener { clickSave(it) }
        binding.imageView.setOnClickListener{selectPicture(it)}
    }
    fun clickSave(view:View)
    {
        val uuid=UUID.randomUUID()
        val imageName="${uuid}.jpg"
        val reference=storage.reference
        val imagesReference=reference.child("images").child(imageName)
        if(selectedImage!=null)
        {
            imagesReference.putFile(selectedImage!!).addOnSuccessListener {
                uploadTask->
                imagesReference.downloadUrl.addOnSuccessListener {
                    uri->
                    if(auth.currentUser!=null)
                    {
                        val downloadUrl=uri.toString()
                        val postMap= hashMapOf<String,Any>()
                        postMap.put("downloadUrl",downloadUrl)
                        postMap.put("email",auth.currentUser!!.email.toString())
                        postMap.put("comment",binding.commentText.text.toString())
                        postMap.put("date",Timestamp.now())
                        db.collection("Posts").add(postMap).addOnSuccessListener {
                            documentReference->
                            //data added to the FirebaseStore
                            val action=UploadFragmentDirections.actionUploadFragmentToFeedFragment()
                            Navigation.findNavController(view).navigate(action)
                        }.addOnFailureListener {
                            exception->
                            Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }.addOnFailureListener {
                exception->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
    fun selectPicture(view:View)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU)
        {
            //Read_Media_Images
            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_MEDIA_IMAGES)
                !=PackageManager.PERMISSION_GRANTED)
            {
                //izin yok
                if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                        Manifest.permission.READ_MEDIA_IMAGES)){
                    //izin mantigini kullaniciya goster
                    Snackbar.make(view,"For using your gallery, you should allow",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Allow",View.OnClickListener {
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                        }).show()
                }else{
                   //izin istememiz lazim
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            }else{
                val intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
        else{ //Read_External_Storage
            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED)
            {
                //izin yok
                if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)){
                    //izin mantigini kullaniciya goster
                    Snackbar.make(view,"For using your gallery, you should allow",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Allow",View.OnClickListener {
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }).show()
                }else{
                    //izin istememiz lazim
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }else{
                val intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }
    private fun registerLaunchers(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
            if(result.resultCode==RESULT_OK){
                val intentFromResult=result.data
                if(intentFromResult!=null)
                {
                    selectedImage=intentFromResult.data
                    try {
                            if(Build.VERSION.SDK_INT>=28){
                                val source=ImageDecoder.createSource(requireActivity().contentResolver,selectedImage!!)
                                selectedBitmap=ImageDecoder.decodeBitmap(source)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }else{
                                selectedBitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,
                                    selectedImage)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){
            result->
            if(result){
                //allow
                val intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
            else{
                //user deny
                Toast.makeText(requireContext(),"You deny, if you pick image, should allow",Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}