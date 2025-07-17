package com.example.cooking.view
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.cooking.databinding.FragmentRecipesBinding
import com.example.cooking.model.Tarif
import com.example.cooking.roomdb.TarifDao
import com.example.cooking.roomdb.TarifDatabase
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream

class RecipesFragment : Fragment() {
    private var _binding:FragmentRecipesBinding?=null
    private val binding get()=_binding!!
    private lateinit var permissionLauncher:ActivityResultLauncher<String>
    /*Yukaridaki ifade izin istemek icin*/
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    /*Yukaridaki ifade galeriye gitmek icin izin istemektedir.*/
    private var selectedImage: Uri?=null/*Bir dosyanin konumunu belirtir. Url'lerde bir Uri'dir. Ornegin=//0data/data/image.jpg gibi*/
    private var selectedBitmap:Bitmap?=null/*Uriyi alip gorsele cevirmek(jpg,png gibi) icin Bitmap kullanilir.*/
    private lateinit var db: TarifDatabase
    private lateinit var tarifDao: TarifDao
    private var selectedRecipe:Tarif?=null
    private val mDisposable=CompositeDisposable()/*Diyelim cok fazla request yapiyoruz. Devamli request yaptigimizda hafizada birikir. Hafizada birikmesin diye temizlemek icin kullanilir. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLauncher()
        db= Room.databaseBuilder(requireContext(),TarifDatabase::class.java,"Tarifler")/*.allowMainThreadQueries()*/.build()
        /*
        Room.databaseBuilder
        1.parametre context
        2.parametre class
        3.parametre bizim verdigimiz_isim. Her yerde aynı isimde kullanmamiz gerekmektedir.*/
        tarifDao=db.tarifDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentRecipesBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener{chooseImage(it)}
        binding.saveButton.setOnClickListener{SaveNew(it)}
        binding.removeButton.setOnClickListener{DeleteRecipes(it)}
        arguments?.let{
            val inform=RecipesFragmentArgs.fromBundle(it).informArg
            if(inform=="yeni"){
                //Adding new recipes
                binding.removeButton.isEnabled=false
                binding.saveButton.isEnabled=true
                binding.yemekIsimText.setText("")
                binding.malzemeText.setText("")
            }
            else{
                //Eski eklenmiş tarif gosteriliyor.
                binding.removeButton.isEnabled=true
                binding.saveButton.isEnabled=false
                val id=RecipesFragmentArgs.fromBundle(it).sentID
                mDisposable.add(
                    tarifDao.findById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse)
                )
            }
        }
    }
    private fun handleResponse(tarif:Tarif)
    {
        binding.yemekIsimText.setText(tarif.isim)
        binding.malzemeText.setText(tarif.malzeme)
        val bitmap=BitmapFactory.decodeByteArray(tarif.gorsel,0,tarif.gorsel.size)
        binding.imageView.setImageBitmap(bitmap)
        selectedRecipe=tarif
    }
    fun SaveNew(view: View){
        val isim=binding.yemekIsimText.text.toString()
        val malzeme=binding.malzemeText.text.toString()
        if(selectedBitmap!=null)
        {
            val smallBitmap=kucukBitmapOlustur(selectedBitmap!!,300)
            val outputStream=ByteArrayOutputStream()//Bitmap, ByteArray dizisine cevirmek kullanilir.
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            /*
                compress(sıkıştırmak) parametreler
                1.parametre=hangi formatta sıkıstirmak
                2.parametre 0-100 arasi bir kalite belirlemek
                3.parametre=outputstream vermek
            */
            val byteDizisi=outputStream.toByteArray()
            val tarif= Tarif(isim,malzeme,byteDizisi)//bir nesne olusturduk.
            mDisposable.add(
                tarifDao.insert(tarif).subscribeOn(Schedulers.io())/*io() internet ve database islemleri icindir.*/
                    .observeOn(AndroidSchedulers.mainThread())/*Sonucu hangi thread de gormek isteriz.*/
                    .subscribe(this::handleResponseForInsert)/*Sonucunda ne olacagini bir fonksiyona atayabilmek icindir. Bu ornekte sonucu handleResponseForInsert*/
            ) /*islemler bittikten sonra dispose olmasini isteriz.*/
        }
    }
    private fun handleResponseForInsert(){
        //bir onceki Fragment'a don
        val action=RecipesFragmentDirections.actionRecipesFragmentToListFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }
    fun DeleteRecipes(view: View){
            if(selectedRecipe!=null)
            {
                mDisposable.add(
                    tarifDao.delete(tarif=selectedRecipe!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseForInsert)
                )
            }
    }
    fun chooseImage(view: View){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_MEDIA_IMAGES)!=PackageManager.PERMISSION_GRANTED)
            /*izin verilmemis. izin istememiz gerekiyor.*/
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_MEDIA_IMAGES))
                /*Kullanici yanlislikla izni vermedi, bu fonksiyonla user'a mantigini soylememiz gerekiyor.*/
                {
                    //snackbar gostermek gerekiyor.userdan neden izin istedigimizi bir kez daha soyleyerek izin istemek gerekiyor.
                    Snackbar.make(view,"Galeriye ulaşmak ve gorsel seçmek gereklidir",Snackbar.LENGTH_INDEFINITE).setAction(
                        "İzin ver",
                        View.OnClickListener { /*izin isteyeceginiz*/
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                        }
                    ).show()
                }
                else{
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            }
            else{
                //izin verilmis,galeriye gidebiliriz.
                val intentToGalLery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalLery)
            }
        }
        else{
            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            /*izin verilmemis. izin istememiz gerekiyor.*/
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_EXTERNAL_STORAGE))
                /*Kullanici yanlislikla izni vermedi, bu fonksiyonla user'a mantigini soylememiz gerekiyor.*/
                {
                    //snackbar gostermek gerekiyor.userdan neden izin istedigimizi bir kez daha soyleyerek izin istemek gerekiyor.
                    Snackbar.make(view,"Galeriye ulaşmak ve gorsel seçmek gereklidir",Snackbar.LENGTH_INDEFINITE).setAction(
                        "İzin ver",
                        View.OnClickListener { /*izin isteyeceginiz*/
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    ).show()
                }
                else{
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
            else{
                //izin verilmis,galeriye gidebiliriz.
                val intentToGalLery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalLery)
            }
        }
    }
    private fun registerLauncher()
    {
        /*Asagida galeriye gitmek icin kullaniriz*/
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode==AppCompatActivity.RESULT_OK){
                val intentFromResult=result.data
                if(intentFromResult!=null)
                {
                    selectedImage=intentFromResult.data/*Uri donmektedir. Uri=User'in sectigi gorselin nerede kayitli oldugu gosterilmektedir.*/
                    try {
                        if(Build.VERSION.SDK_INT>=28)
                        {
                            /*API 28 ve ust surumler icin*/
                            val imageSource=ImageDecoder.createSource(requireActivity().contentResolver,selectedImage!!)
                            selectedBitmap=ImageDecoder.decodeBitmap(imageSource)
                            binding.imageView.setImageBitmap(selectedBitmap)
                        }
                        else{
                            /*API 28'den alt surumler icin*/
                            selectedBitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,selectedImage)/*Parametre olarak contentResolver almaktadir.*/
                            binding.imageView.setImageBitmap(selectedBitmap)/*Yukaridaki kod ile birlikte eski yontem*/
                        }
                    }
                    catch (e:Exception)
                    {
                        println(e.localizedMessage)
                    }
                }
            }
        }
        /*Bir contract ve bir callback ihtiyacimiz vardir. Contract icin StartActivityForResult enum secilir.
        intent ile bir aktivite paylasir gibi ama sonucu bize geri donecek. Galeriye gidecegiz, galerinin sonucu bize geri donecek
        Bu enum activityresult donmektedir. result ile sonucu karsilariz
        ---------------------------------*/
        /*Asagida izin istemek icin kullaniriz*/
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission(),{result->
            /*Oncellikle registerForActivityResult cagrilir.
            ilk parametre contract ister. ikinci parametre callback ister.
            contract izin istemekte olabilir, galeriye gitmekte olabilir.
            ActivityResultContracts. bir enumdur. Ne islem yapilacagi sorulmaktadir.
            TakePicture=fotograf cek vb. Biz burada izin isteyecegimiz icin RequestPermission kullandik.
            callback, sonunda ne olacak. Birinci parametrede sectigimiz enum sonucu ne olacak?
            Ornekte RequestPermission boolean donecektir. boolean, result olarak karsilandi
            ActivityResultCallback-->bir interface*/
            if(result){
                //izin verildi
                //galeriye gidebiliriz.
                val intentToGalLery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalLery)
            }
            else{
                //izin verilmedi
                Toast.makeText(requireContext(),"İzin verilmedi",Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun kucukBitmapOlustur(selectedBitmap: Bitmap,maximumBoyut:Int) : Bitmap
    {
        var guncelWidth=selectedBitmap.width
        var guncelHeight=selectedBitmap.height
        val bitmapOrani:Double=guncelWidth.toDouble()/guncelHeight.toDouble()
        if(bitmapOrani>1){
            //gorsel yataydir
            guncelWidth=maximumBoyut
            val kisatilmisHeight=guncelWidth/bitmapOrani
            guncelHeight=kisatilmisHeight.toInt()
        }
        else
        {
            //gorsel dikeydir
            guncelHeight=maximumBoyut
            val kisatilmisWidth=guncelHeight*bitmapOrani
            guncelWidth=kisatilmisWidth.toInt()
        }
      return Bitmap.createScaledBitmap(selectedBitmap,guncelWidth,guncelHeight,true)
        /* createScaledBitmap
        1.Parametre=kullanilmasi gereken Bitmap
        2.parametre=genislik
        3.parametre=uzunluk
        4.parametre=filter
        * */
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
        mDisposable.clear()/*disposable bu sekilde temizlenir.Clear yerine dispose kullanmak hatali olur.*/
    }
}