package com.example.navigationandlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationandlist.model.Food
import com.example.navigationandlist.ui.theme.NavigationAndListTheme

@Composable
fun DetailMonitoring(food:Food){
    Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
        ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(food.foodName,
                modifier=Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color= Color.Black,
                textAlign = TextAlign.Center
                )
            Image(bitmap=ImageBitmap.imageResource(id=food.foodImage), contentDescription =food.foodName,
                modifier=Modifier.padding(16.dp).size(300.dp,200.dp))
            Text(text=food.foodRecipes,
                style = MaterialTheme.typography.headlineSmall,
                modifier=Modifier.padding(2.dp),
                color=Color.Black,
                textAlign = TextAlign.Center
                )
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DetailPreview(){
    NavigationAndListTheme {
        val pizza=Food("Pizza","Hamur,Peynir,Domates Sosu",R.drawable.pizza)
        val makarna=Food("Makarna","Penne,Salca,Feslegen",R.drawable.makarna)
        val kofte=Food("Kofte","Kiyma",R.drawable.kofte)
        val salata=Food("Salata","Marul,Salatalık,Domates",R.drawable.salata)
        val ekmek=Food("Ekmek","Un,Bugday",R.drawable.ekmek)
        DetailMonitoring(food=ekmek)
    }
}*/