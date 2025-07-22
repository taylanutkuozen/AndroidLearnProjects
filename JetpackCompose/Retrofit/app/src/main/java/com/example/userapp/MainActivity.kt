package com.example.userapp

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.userapp.model.User
import com.example.userapp.screens.DetailScreen
import com.example.userapp.screens.UserList
import com.example.userapp.ui.theme.UserAppTheme
import com.example.userapp.viewmodel.UserViewModel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val viewModel : UserViewModel by viewModels<UserViewModel>()
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
                            composable("user_list_screen"){
                                viewModel.getUsers()
                                UserList(userList = viewModel.userList.value, navController = navController)
                            }
                            composable("detail_screen/{selectedUser}",
                                    arguments = listOf(
                                        navArgument("selectedUser"){
                                            type= NavType.StringType
                                        }
                                    )
                                ){
                                      val userString=remember{
                                                it.arguments?.getString("selectedUser")
                                      }
                                      val selectedUser=Gson().fromJson(userString, User::class.java)
                                      DetailScreen(user=selectedUser)
                            }
                        }
                }
            }
        }
    }
}
