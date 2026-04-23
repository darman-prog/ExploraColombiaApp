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
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myNavController = rememberNavController()
            val auth = com.google.firebase.Firebase.auth
            val startRoute = if (auth.currentUser != null) "home" else "login"

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
                composable(route = "home") {    HomeScreen(
                    onLogout = {
                        myNavController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )

                }
            }
        }
    }
}


