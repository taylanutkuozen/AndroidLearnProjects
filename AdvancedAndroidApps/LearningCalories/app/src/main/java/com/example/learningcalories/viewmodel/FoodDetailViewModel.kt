package com.example.learningcalories.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningcalories.model.Food
import com.example.learningcalories.roomdb.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application):AndroidViewModel(application) {
    val foodLiveData=MutableLiveData<Food>()
    fun dataFromRoom(uuid:Int){
        viewModelScope.launch { //this:CoroutineScope
            val dao= FoodDatabase(getApplication()).foodDao()
            val food=dao.getFood(uuid)
            foodLiveData.value=food
        }
    }
}