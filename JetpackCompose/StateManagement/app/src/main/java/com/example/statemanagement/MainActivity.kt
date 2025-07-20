package com.example.statemanagement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.statemanagement.ui.theme.StateManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateManagementTheme {
                Scaffold(modifier=Modifier.fillMaxSize()){
                    innerPadding->
                    Box(modifier=Modifier.padding(innerPadding)){
                        MainScreen()
                    }
                }
            }
        }
    }
}
//@SuppressLint("UnrememberedMutableState")//2.TextField ornegi
@Composable
fun MainScreen(){
     Column(
         modifier=Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ){

         /*EditText yani TextField userdan aldigimiz veriler oldugu ve anlik degismesini bekledigimiz icin
         farkli bir degisken ve mutable kullanacagiz.*/
         var userInput= remember {mutableStateOf("")}//remember ile recomposition yaptik
         var textValue=remember{ mutableStateOf("Hellooo Android") }
         var paddingValue= remember { mutableStateOf(10.dp) }
         var userName= remember { mutableStateOf("Please enter user name") } //state hoisting
         var userMail= remember { mutableStateOf("Please enter user mail") }
         //var userInput= mutableStateOf("Please submit your age") //Sadece bu satirdaki ornek icin unremembered olarak baskiladik.
         SpecialText(string = "Hi Android")
         Spacer(modifier=Modifier.padding(10.dp))
         SpecialText(string = "Hello Android")
         Spacer(modifier=Modifier.padding(10.dp))
         SpecialText(string = "Kayit Formu")
         Spacer(modifier = Modifier.padding(10.dp))
         Spacer(modifier=Modifier.padding(10.dp))
         //SpecialTextField("User Name") 1.durum button ile veriyi tasiyamiyoruz.
         SpecialTextField2(string = userName.value, onValueChangedFunction = {
            userName.value=it
         })
         Spacer(modifier=Modifier.padding(10.dp))
         //SpecialTextField("User Maili") 1.durum button ile veriyi tasiyamiyoruz.
         SpecialTextField2(string = userMail.value, onValueChangedFunction = {
             userMail.value=it
         })
         Spacer(modifier=Modifier.padding(10.dp))
         Button(onClick = {
                userName.value="utkuu"
                userMail.value="utkuozen01@gmail.com"
             /*State hoisting ile button araciligi ile ozel olusturulan item degerleri tasinabiliyor,
             degistirilebiliyor.*/
         }){
             Text("Save User")
         }
         TextField(value=userInput.value, onValueChange = {
             userInput.value=it//userInput string degil MutableState<String> oldugundan value alinir ve string olur
         }, placeholder={
             Text("Please submit your age")
         })
         /*TextField(value="Please submit your age", onValueChange ={//it->
             println(it)
         } )ilk parametre degerim ne, ikinci parametre, deger degistiginde ne yapmaliyim*/
            /*Userdan bilgi almak icin kullanilan yapi, EditText*/
            Spacer(modifier = Modifier.padding(paddingValue.value))
            Text(textValue.value)
            Spacer(modifier = Modifier.padding(paddingValue.value))
            Button(onClick={
                textValue.value="Hellooo Utkuuu"
                paddingValue.value=20.dp
            }, enabled = true)/*onClick zorunlu*/ {
                Text("Example Button")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Image(bitmap = ImageBitmap.imageResource(id=R.drawable.taksilazimdir), contentDescription = "taksi lazimdir agabey",
                modifier = Modifier.size(250.dp,100.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            Image(imageVector = ImageVector.vectorResource(id=R.drawable.ic_launcher_background),
                contentDescription = "taksi lazimdir agabey")/*ImageVector=bir tasarimci ile calisirken, alinan gorseler ile ilgili calismalar*/
            Spacer(modifier = Modifier.padding(10.dp))
            Image(painter = ColorPainter(Color.Blue),
                contentDescription = "cizim",
                modifier = Modifier.size(100.dp,100.dp))/*Gorsel olusturup kendimiz cizmek istiyorsak*/
     }
}
@Composable
fun SpecialTextField2(string: String,onValueChangedFunction:(String)->Unit){ //State yok icerisinde
    TextField(value = string, onValueChange = onValueChangedFunction)
}
@Composable
fun SpecialTextField(string: String){
    var myState= remember { mutableStateOf(string) }
    TextField(value = myState.value, onValueChange = {//it->
        myState.value=it
    })
}
@Composable
fun SpecialText(string: String){
    Text(text=string,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Italic
        )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateManagementTheme {
        MainScreen()
    }
}
/*State hoisting

*/