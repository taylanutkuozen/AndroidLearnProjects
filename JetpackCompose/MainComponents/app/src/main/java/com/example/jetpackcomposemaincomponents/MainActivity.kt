package com.example.jetpackcomposemaincomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposemaincomponents.ui.theme.JetpackComposeMainComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeMainComponentsTheme { //hersey tema ile birlikte basliyor.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> //innerPadding mecburidir. Telefonla ekran arasinda ne kadar bosluk olacagini otomatik veriyor.
                    /*Scaffold uygulamanin gorunumunun arayuzunun bir iskeleti(on yuzunun iskeleti), composable fonksiyon
                    * **************
                    Ctrl+click ile kotlinde hazir class icerisine gidebiliyoruz.*/
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    Box(modifier=Modifier.padding(innerPadding)) //modifier bir compose ile calisirken, boyutu, arkaplan rengi, tiklanir olacak mi, padding olacak mi, view ile ilgili parametreler
                    {
                        MainScreen()
                    }
                }
            }
        }
        lambdaTestiFunction(10,::testFunction)/*:: ile bir function reference olarak verilir.*/
        //veya
        lambdaTestiFunction {
            testFunction()
        }
        //veya diyelim ki integer default degeri yok
        lambdaTestiFunction(10) {
            testFunction()
        }
    }
    fun lambdaTestiFunction(int:Int=5,myFuncParam:()->Unit)
    {
        myFuncParam.invoke()/*parametredeki function calisti*/
        /*testFunction hem parametre almiyor, hemde geriye birsey dondurmuyor
        boyle yazildi
        Ornegin integer parametre alip geriye string donduruyor olsaydi
        myFuncParam(Int)->String olurdu.*/
    }
    fun testFunction(){
        println("test")
    }
}
/*Bir function icerisine baska bir function parametre olarak alabilir.
------------

*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*Modifier parametreleri degistirmemize, boyutunu degistirmemize saglayan bir yapi veriyor.*/
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/
@Composable
fun MainScreen(){
    Column(
        modifier = Modifier.fillMaxSize()/*butun ekrani kaplama komutu*/
            .background(Color.LightGray),
        //verticalArrangement = Arrangement.SpaceBetween,//Birini en basa birini en sona yazip esit bosluk olacak sekilde yaymaya calisiyor.
        //verticalArrangement = Arrangement.SpaceEvenly //esit bosluk olacak sekilde yaymaya calisir.
        //verticalArrangement = Arrangement.SpaceAround//Yaymaya calisir
        verticalArrangement = Arrangement.Center, //merkezleme
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text="Merhaba D", color = Color.Red, fontWeight = FontWeight.Bold,
            fontSize = 26.sp)
        Spacer(modifier = Modifier.padding(50.dp))//Spacer ile iki item arasinda bosluk birakabiliyoruz. modifier ve padding kullanarak.
        SpecialText("Merhaba Utku")
        Spacer(modifier = Modifier.padding(30.dp))
        SpecialText("Merhaba OZEN")
        Row(
            modifier=Modifier.fillMaxWidth().background(Color.Yellow),//Yatay olarak butun genisligi doldur
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(text="Row item 1")
            Text(text="Row item 2")
        }
    }
    /*
   Row(
       // modifier = Modifier.fillMaxSize()//butun ekrani kaplama komutu
   ){
       Text(text="Merhaba Compose", color = Color.Blue, fontWeight = FontWeight.Bold)
       Text(text="Merhaba Utku", color = Color.Black
           , fontWeight = FontWeight.Bold, fontSize =18.sp)
   }

TextField=EditText user'in kullandigi
Text=TextView, bir metin paylasmak icin kullanilan*/
}
@Preview(showBackground = true)
/*Yazilan kodlarin emulatorde calistirilmadan once bir on yuzunun yapilmasi*/
@Composable
fun GreetingPreview() {
    JetpackComposeMainComponentsTheme {
        //Greeting("UTKU")
        MainScreen()
    }
}