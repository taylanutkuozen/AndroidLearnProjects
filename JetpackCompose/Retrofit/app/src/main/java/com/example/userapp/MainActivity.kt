package com.example.userapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.userapp.model.Address
import com.example.userapp.model.Company
import com.example.userapp.model.Geo
import com.example.userapp.model.User
import com.example.userapp.screens.DetailScreen
import com.example.userapp.screens.UserList
import com.example.userapp.ui.theme.UserAppTheme
import com.example.userapp.viewmodel.DetailViewModel
import com.example.userapp.viewmodel.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel : UserViewModel by viewModels<UserViewModel>()
    private val detailViewModel:DetailViewModel by viewModels<DetailViewModel>()
    @SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            UserAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier=Modifier.padding(innerPadding))
                        NavHost(navController=navController, startDestination ="user_list_screen" )
                        {
                            composable("user_list_screen") {
                                viewModel.getUsers()
                                UserList(
                                    userList = viewModel.userList.value,
                                    navController = navController
                                )
                            }
                            composable("detail_screen/{selectedUser}",
                                arguments = listOf(
                                    navArgument("selectedUser") {
                                        type = NavType.IntType
                                    }
                                )
                            ) {
                                val userIndex = remember {
                                    it.arguments?.getInt("selectedUser")
                                }
                                /*val scope= rememberCoroutineScope()//snackbar kullanima uygundur,genel buton click degil
                                      var selectedUser:User?=null
                                      scope.launch(Dispatchers.IO) {
                                          selectedUser=detailViewModel.getSingleUser(userIndex?:0)
                                      }
                                      selectedUser?.let {
                                          DetailScreen(user=selectedUser!!)
                                      }
                                      Bu kod yanlis hem User ekrana gelmiyor, hemde veri birden cok kez geliyor
                                      val selectedUser=remember{
                                        mutableStateOf(User(1,"","","",
                                        Address("","","","", Geo("","")),
                                        "","", Company("","","")))}
                                    }
                                    scope.launch(Dispatchers.IO) {
                                          selectedUser.value=detailViewModel.getSingleUser(userIndex?:0)
                                      }
                                      DetailScreen(user=selectedUser.value)
                                      State yapsak bile 8-9 kez istek atiyor.
                                      LaunchEffect(true) tehlikelidir=while(true) gibi
                                      */
                                val selectedUser = remember {
                                    mutableStateOf(
                                        User(
                                            1, "", "", "",
                                            Address("", "", "", "", Geo("", "")),
                                            "", "", Company("", "", "")
                                        )
                                    )
                                }/*
                                LaunchedEffect(key1 = Unit) { /*LaunchEffect Composition bagli coroutine kapsamlarini yonetir. Launch effect burada key1 baglidir.*/
                                    selectedUser.value =
                                        detailViewModel.getSingleUser(userIndex ?: 0)
                                }*/
                                //2.yol
                                val selectedUser2= produceState(initialValue = User(
                                    1, "", "", "",
                                    Address("", "", "", "", Geo("", "")),
                                    "", "", Company("", "", "")
                                )){
                                    value=detailViewModel.getSingleUser(userIndex ?: 0)
                                }
                                //DetailScreen(user = selectedUser.value)//LaunchedEffect kullanilan dogru yol-1
                                DetailScreen(user=selectedUser2.value)//produceState kullanilan dogru yol-2
                            }
                        }
                }
            }
        }
    }
}
/*
github.com/android/compose-samples
ArtBookCompose incelenebilir.
Compose UI temalari=MaterialTheme
NavHost sayfalar arasinda yonlendirme saglar.
Ic ice sadece @Composable tanimlamasi yeterli olarak item tanimlanabilir.
LazyRow-->Yatay kaydirilabilir liste
LazyColumn-->Dikey kaydirilabilir liste
Text()-->Hem tekli hemde coklu satirlari gosterir.
*/