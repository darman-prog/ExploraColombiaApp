package com.example.diegomeza.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diegomeza.myapplication.ui.elements.AddPlaceScreen
import com.example.diegomeza.myapplication.ui.elements.HomeScreen
import com.example.diegomeza.myapplication.ui.elements.LoginScreen
import com.example.diegomeza.myapplication.ui.elements.RegisterScreen
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val auth = com.google.firebase.Firebase.auth
            val startRoute = if (auth.currentUser != null) "home" else "login"
            val myNavController = rememberNavController()

            NavHost(
                navController = myNavController,
                startDestination = startRoute,
                modifier = Modifier.fillMaxSize()
            ) {

                composable(route = "login") {
                    LoginScreen(
                        onLoginSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            myNavController.navigate("register")
                        }
                    )
                }
                composable(route = "register") {
                    RegisterScreen(
                        onRegisterSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToLogin = {
                            myNavController.navigate("login")
                        },
                        onBackClick = {
                            myNavController.popBackStack()
                        }
                    )
                }

                composable(route = "home") {
                    HomeScreen(OnClickAddPlace = {
                        myNavController.navigate("add_place")
                    })

                }

                composable (route="add_place"){
                    AddPlaceScreen(onBackClick = {
                        myNavController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    })
                }

            }
        }
    }
}
