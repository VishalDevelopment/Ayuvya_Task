package com.example.taskapp

sealed class Routes (val route:String){
    object HomeScreen : Routes("home")
    object CartScreen: Routes("cart")
}