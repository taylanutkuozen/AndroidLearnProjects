package com.example.mapsproject

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mapsproject.databinding.ActivityMapsBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMapLongClickListener { //OnMapReady fonksiyonu OnMapReadyCallbackten geliyor.
 //OnMapReady fonksiyonu uygulama ayaga kalkar iken aciliyor.
    //OnMapLongClickListener=Map uzerinde user'in click ettigi yeri bulma. User uzun basar ise, 2-3 saniye elini tutarsa
    private lateinit var mMap: GoogleMap //Harita ile baglanmis bu nesne.
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationManager: LocationManager /*Bir class. Konumun nereden alinacagi*/
    private lateinit var locationListener: LocationListener /*Bir interface. konumda degisiklik oldugunde dinler*/
    private lateinit var permissionLauncher:ActivityResultLauncher<String>
    var followBoolean:Boolean?=null
    private lateinit var sharedPrefences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager //supportFragmentManager ile Navigation ihtiyac duymadan ele alabiliyoruz.
            .findFragmentById(R.id.map) as SupportMapFragment //Activity icerisinde bir tane Fragment var, halihazirda harita ile ilgili
        mapFragment.getMapAsync(this)
        registerLauncher()
        sharedPrefences=getSharedPreferences("com.example.mapsproject", MODE_PRIVATE)
        followBoolean=false
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(this)
        locationManager=this.getSystemService(LOCATION_SERVICE) as LocationManager/*Sistemde hazir olan servisleri getirir-->getSystemService()*/
        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) { //konum degistiginde cagrilmasi gerekli fonksiyon
                followBoolean=sharedPrefences.getBoolean("followBoolean",false)//key ve defValue'su false
                if(!followBoolean!!)
                {
                    //mMap.clear()-->marker kaldirilir.
                    val userLocation=LatLng(location.latitude,location.longitude)
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Marker in my location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,14f))
                    sharedPrefences.edit().putBoolean("followBoolean",true).apply() //islem biterken sharedPrefences uzerinde islem yapariz.
                }
            }
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                Snackbar.make(binding.root,"Please allow the location permission",Snackbar.LENGTH_INDEFINITE).setAction(
                    "Allow"){ //it:View
                        //request permission
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }.show()
            }else{
                //request permission
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)//en iyi sonucu GPS_PROVIDER verir.
            /*2.parametre ne kadar zamanda cok az,kisa olursa pil omru tukenir.
            * 3.parametre ne kadar mesafe*/
            val lastKnownLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) //nullable donuyor.
            if( lastKnownLocation!=null){
                val lastknownLocLatLng=LatLng(lastKnownLocation.latitude,lastKnownLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastknownLocLatLng,14f))
            }
        }
            /*36.590686, 36.166173
        val iskenderun = LatLng(36.590686, 36.166173) //LatLng= latitude longitude
        mMap.addMarker(MarkerOptions().position(iskenderun).title("Marker in Iskenderun"))//marker,pin,isaretci. Isaretciye tiklarsaniz navigasyonda gelir.
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(iskenderun))//acilistaki konum
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iskenderun,14f))//focusluyor, float ile zoom parametresi verilir.
        */
    }
    private fun registerLauncher(){
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){//it:Boolean!
            result->
            if(result){
                if(ContextCompat.checkSelfPermission(this@MapsActivity,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)//en iyi sonucu GPS_PROVIDER verir.
                    val lastKnownLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) //nullable donuyor.
                    if( lastKnownLocation!=null){
                        val lastknownLocLatLng=LatLng(lastKnownLocation.latitude,lastKnownLocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastknownLocLatLng,14f))
                    }
                }
            }else{
                Toast.makeText(this@MapsActivity,"should allow location permission",Toast.LENGTH_LONG).show()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onMapLongClick(p0: LatLng) {//p0 parametresi click edildigi yeri LatLng olarak verir.
        mMap.clear()
        //println(p0.latitude)
        //println(p0.longitude)
        val geocoder=Geocoder(this, Locale.getDefault())//Geocoder icerisinden alacagiz adresi
        var address=""
        try {
            geocoder.getFromLocation(p0.latitude,p0.longitude,1,Geocoder.GeocodeListener { addressList->
            val firstAddress=addressList.first()
            val countryName=firstAddress.countryName
            val streetNumber=firstAddress.subThoroughfare
            val street=firstAddress.thoroughfare
            address=countryName+" "+street+" "+streetNumber
            println(address)
            })//1 ve 2.parametre latitude longitude 3.parametre max result=Birebir adresi bulamayabilir,Adres listesi doner

        }catch (e:Exception)
        {
            e.printStackTrace()
        }
        mMap.addMarker(MarkerOptions().position(p0))
    }
}
/*
* Launcher activity ne ise; android:exported="true"
* AndroidManifest.xml icerisinde Main activity sildik. MapActivity baslamasini istiyoruz. intent-filter map activity nin altina ekledik.
* MainActivity delete edildi, layout icerisinden activity_main delete edildi.
* Keys and credentials icerisinden google map bilgilerini bulabiliyoruz.
* Google Map araciligi ile istedigimiz konumu secip gerek markera tikladiktan sonra url ye bakarak gerek sag click ile latitude ve longitude bulabiliriz.
* android permissions-->developer.android.com/reference/android/manifest_permission
* Tam konum icin-->ACCESS_FINE_LOCATION
* Sadece mahalle,sokak vs. icin--> ACCESS_COARSE_LOCATION
* */