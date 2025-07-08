package com.example.learningcalories.viewmodel
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningcalories.model.Food
import com.example.learningcalories.roomdb.FoodDAO
import com.example.learningcalories.roomdb.FoodDatabase
import com.example.learningcalories.services.FoodAPIService
import com.example.learningcalories.util.PrivateSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class FoodListViewModel(application: Application):AndroidViewModel(application) { //requireContext veya requireApplication
    val foods=MutableLiveData<List<Food>>()//degistirilebilir Live Data
    val foodError=MutableLiveData<Boolean>()
    val foodLoading=MutableLiveData<Boolean>()
    private val updatedTime=10*60*1000*1000*1000L//10dakika-60saniye-1000sanise-1000microsanise-1000nanosanise
    private val foodApiService=FoodAPIService()
    private val privateSharedPreferences=PrivateSharedPreferences(getApplication())/*getApplication() kullanabilmek icin AndroidViewModel kullanmak daha uygun olacaktir.*/
    fun refreshData(){
        val savingTime=privateSharedPreferences.getTime()
        if(savingTime!=null&&savingTime!=0L&&System.nanoTime()-savingTime<updatedTime){
            getDataFromRoom()
        }else{
            getDataFromInternet()
        }
    }
    fun refreshDataFromInternet(){
        getDataFromInternet()
    }
    private fun getDataFromRoom(){
        foodLoading.value=true
        viewModelScope.launch {
                val foodList=FoodDatabase(getApplication()).foodDao().getAllFood()
                showFoods(foodList)
                Toast.makeText(getApplication(),"Food information from Room",Toast.LENGTH_LONG).show()
            }
        }
    private fun getDataFromInternet(){
        foodLoading.value=true
        viewModelScope.launch(Dispatchers.IO) { //this:CoroutineScope
            val foodsList=foodApiService.getData()
            withContext(Dispatchers.Main){
                foodLoading.value=false
                saveRoom(foodsList)
                Toast.makeText(getApplication(),"Foods information pull from internet",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun showFoods(foodList: List<Food>){
        foods.value=foodList
        foodError.value=false
        foodLoading.value=false
    }
    private fun saveRoom(foodList:List<Food>){
        viewModelScope.launch {
            val dao=FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList=dao.insertAllFood(*foodList.toTypedArray())
            var i=0
            while(i<foodList.size){
                foodList[i].uuid=uuidList[i].toInt()
                i=i+1
            }
            showFoods(foodList)
        }
        privateSharedPreferences.saveTime(System.nanoTime())
    }
}