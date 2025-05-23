package com.example.nedirbucontext

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nedirbucontext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*Row row sira sira gosterilen her sey=RecyclerView, mailler, muzikler, tek bir sira(bir tane) xmlde hazirlariz.
    Bu siradan kac tane istiyorsak android otomatik ciziyor. Recycler view birbirine baglamak*/
    //SharedPreferences=Bu yapı bir XML dosyasi olusturur. Kullanilan cihazın hafizasina saklanir. Key-Value Pairing. Bir-iki veri saklamak icin. Veritabani degil. Tek bir sey kaydetmek icin mantikli.
    lateinit var sharedPreferences: SharedPreferences
    var alinanKullaniciAdi : String?=null
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Toast.makeText(this,"Hosgeldiniz", Toast.LENGTH_SHORT).show()/*API 30'dan sonrasi*/
        //Toast.makeText(this@MainActivity,"Hosgeldiniz", Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext,"Hosgeldiniz",Toast.LENGTH_LONG).show()
        /*binding.button.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                println("buttona tıklandı")
                this@MainActivity//burada artik this@MainActivity kullanilmasi gerekir
            }

        })
        binding.button.setOnClickListener{

        }*/
        sharedPreferences = this@MainActivity.getSharedPreferences("com.example.nedirbucontext",
            Context.MODE_PRIVATE)/*Mode_private olusturdugumuz uygulamanin dosyalarina baska hicbir uygulama erisemez.*/
        alinanKullaniciAdi=sharedPreferences.getString("Isim","") /*2.parametre defValue'dur. Aradigimiz anahtarda bir deger yoksa ne yapilmasi icin vardir.*/
        if(alinanKullaniciAdi=="")
        {
            binding.textView.text="Kaydedilen isim: "
        }
        else
        {
            binding.textView.text="Kaydedilen isim: ${alinanKullaniciAdi}"
        }
    }
    fun kaydet(view: View)
    {
      val alert=AlertDialog.Builder(this@MainActivity)/*ilk durumda applicationContext verdik. hata olustu cünkü AppCompatActivity ile theme uyusmadı*/
      alert.setTitle("Kayıt Et")
      alert.setMessage("Kayıt etmek istediğinize emin misiniz?")

      alert.setPositiveButton("Evet") { dialog, which ->
          Toast.makeText(this@MainActivity,"Kayıt edildi",Toast.LENGTH_LONG).show()
      }
      //alert.setNeutralButton()
      alert.setNegativeButton("Hayır",object:DialogInterface.OnClickListener{
          override fun onClick(dialog: DialogInterface?, which: Int) {
              Toast.makeText(this@MainActivity,"Kayıt iptal edildi",Toast.LENGTH_LONG).show()
          }
      })
     alert.show()
    }
    fun veriKaydet(view:View)
    {
        val kullaniciIsmı=binding.editText.text.toString()
        if(kullaniciIsmı=="") {
            Toast.makeText(this@MainActivity, "İsminizi boş bırakmayınız", Toast.LENGTH_LONG).show()
        }
        else
        {
            sharedPreferences.edit().putString("Isim",kullaniciIsmı).apply()
            /*putInt,putString vs olarak cagirabiliriz. Key-value pair eslesmesi icin 2 parametre vardir. apply() ile kaydederiz.*/
        }
        binding.textView.text="Kaydedilen isim: ${kullaniciIsmı}"
    }
    fun veriSil(view: View)
    {
        alinanKullaniciAdi = sharedPreferences.getString("Isim","")
        if(alinanKullaniciAdi!="")
        {
            sharedPreferences.edit().remove("Isim").apply()
        }
        binding.textView.text="Kaydedilen Isim: "
    }
    fun sayilariTopla(view:View)
    {
        val sayi1=binding.editTextNumber.text.toString()
        val sayi2=binding.editTextNumber2.text.toString()
        val sayi3=sayi1.toDouble()+sayi2.toDouble()
        binding.textView2.text="Sonuç: ${sayi3}"

    }
    fun sayilariCikar(view: View)
    {
        val sayi1=binding.editTextNumber.text.toString()
        val sayi2=binding.editTextNumber2.text.toString()
        val sayi3=sayi1.toDouble()-sayi2.toDouble()
        binding.textView2.text="Sonuç: ${sayi3}"
    }
    fun sayilariCarp(view:View)
    {
        val sayi1=binding.editTextNumber.text.toString()
        val sayi2=binding.editTextNumber2.text.toString()
        val sayi3=sayi1.toDouble()*sayi2.toDouble()
        binding.textView2.text="Sonuç: ${sayi3}"
    }
    fun sayilariBol(view:View)
    {
        var sayi1:Double=1.0
        if(binding.editTextNumber.text.toString()!=null)
        {
            sayi1=binding.editTextNumber.text.toString().toDouble()
        }
        else
        {
            binding.textView2.text="İlk sayiyi giriniz"
        }
        var sayi2:Double=1.0
        if(binding.editTextNumber2.text.toString()!=null)
        {
            sayi2=binding.editTextNumber2.text.toString().toDouble()
        }
        else
        {
            binding.textView2.text="İkinci sayiyi giriniz"
        }
        if(sayi1!=null&&sayi2!=null)
        {
            val sayi3=(sayi1.toDouble())/(sayi2.toDouble())
            binding.textView2.text="Sonuç: ${sayi3}"
        }
    }
}
interface Hayvan {
    fun sesCikar()
}
class Kopek:Hayvan
{
    override fun sesCikar() {
        TODO("Not yet implemented")
    }

}