package com.example.navigationandlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navigationandlist.model.Food
import com.example.navigationandlist.ui.theme.NavigationAndListTheme
import com.google.gson.Gson

/*Lazy Column Compose*/
@Composable
fun FoodList(foods:List<Food>,navController: NavController){
    LazyColumn(contentPadding = PaddingValues(5.dp), modifier=Modifier.fillMaxSize().
    background(color= MaterialTheme.colorScheme.background))
    {
        items(foods){//it->
            FoodRow(food = it,navController)
        }
    }
}
@Composable
fun FoodRow(food:Food,navController: NavController){
    Column(modifier=Modifier.fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.background)
        .clickable {
            navController.navigate("detail_monitoring/${Gson().toJson(food)}")//navigation,Gson library ile Json cevirdik.
        })
    {
        Text(text=food.foodName,
            style= MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Thin
        )
        Text(text=food.foodRecipes,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(5.dp)
            )
    }
}
/*
@Preview(showBackground = true)
@Composable
fun FoodListPreview(){
    NavigationAndListTheme {
        val foodList= arrayListOf<Food>()
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
        FoodList(foods = foodList)
    }
}*/