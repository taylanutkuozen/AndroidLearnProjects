package com.example.navigationandlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationandlist.model.Food
import com.example.navigationandlist.ui.theme.NavigationAndListTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val foodList=ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()//Navigation kullanimi(implement edilen library)
            NavigationAndListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier=Modifier.padding(innerPadding)){
                        NavHost(navController=navController, startDestination = "list_monitoring") {
                            composable("list_monitoring") {
                                createData()
                                FoodList(foods =foodList,navController=navController)
                            }
                            composable("detail_monitoring/{selectedFood}",
                                arguments = listOf(
                                        navArgument("selectedFood"){
                                            type= NavType.StringType
                                        }
                                    )
                                )
                            {
                                val foodString=remember{//it->NavBackStackEntry
                                    it.arguments?.getString("selectedFood")
                                }
                                val selectedFood= Gson().fromJson(foodString,Food::class.java)//sana bir string gelecek, onu ilgili classa cevir.
                                DetailMonitoring(food=selectedFood)
                            }
                        }
                    }
                }
            }
        }
    }
    private fun createData(){
        val pizza=Food("Pizza","Hamur,Peynir,Domates Sosu",R.drawable.pizza)
        val makarna=Food("Makarna","Penne,Salca,Feslegen",R.drawable.makarna)
        val kofte=Food("Kofte","Kiyma",R.drawable.kofte)
        val salata=Food("Salata","Marul,Salatalık,Domates",R.drawable.salata)
        val ekmek=Food("Ekmek","Un,Bugday",R.drawable.ekmek)
        foodList.add(pizza)
        foodList.add(makarna)
        foodList.add(kofte)
        foodList.add(salata)
        foodList.add(ekmek)
    }
}
/*
implementation("com.google.code.gson:gson:2.11.0")=bu kutuphane bir model alip
gsona cevirir diger ekranda gsondan alip monitore cevirir.
*/