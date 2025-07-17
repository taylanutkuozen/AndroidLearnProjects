package com.example.jetpackcomposemaincomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpecialText(text:String){
    Text(modifier = Modifier.clickable {
        println("text tiklandi")
    }.background(color= Color.Blue)
        .fillMaxWidth(0.9f)//bunun yerine 0.9f-->fraction ekran genisliginin kacta kacini almak istiyorsa bu item
        .padding(top=10.dp, start = 3.dp, end = 3.dp, bottom = 5.dp)/*Text arka planina gore pozisyonlama*/
        //.width(200.dp) --> sabit bir width veya height kullanmak istiyorsak
        //.height(100.dp)
        ,text=text,
        color = Color.White,
        textAlign = TextAlign.Center,//Text ortada olsun
        fontSize = 26.sp
        )
}
@Preview(showBackground = true)
@Composable
fun SpecialPreview(){
    SpecialText("MERHABA UTKU")
}