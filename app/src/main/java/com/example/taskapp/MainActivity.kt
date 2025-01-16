package com.example.taskapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import com.example.taskapp.Screens.CartScreen
import com.example.taskapp.Screens.HomeScreen
import com.example.taskapp.ViewModel.TaskViewModel
import com.example.taskapp.ui.theme.TaskAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val viewmodel : TaskViewModel = hiltViewModel()
                        val products = remember {
                            mutableStateOf(
                                listOf(
                                    ProductType(1, R.drawable.product1, "Coffee", "5"),
                                    ProductType(2, R.drawable.product2, "Earbuds", "2"),
                                    ProductType(3, R.drawable.product3, "Cleaner", "3")
                                )
                            )
                        }
                        LaunchedEffect(Unit) {
                            products.value.forEach {
                                viewmodel.insertItem(ItemType(it.id, it.imageVector, it.name, it.quantity))
                                Log.d("MAINACTIVITY", "Inserting: $it")
                            }
                        }

                        val count = viewmodel.getCartCount().collectAsState(initial = 0)
                        Start_App(count)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Start_App(count: State<Int>) {

    val startdestination = Routes.HomeScreen.route
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Task App")
            }, windowInsets = TopAppBarDefaults.windowInsets, actions = {
                Text(
                    text = "${count.value}",
                    modifier = Modifier.padding(horizontal = 5.dp),
                    color = Color.Black
                )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                    contentDescription = null, modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .clickable {
                            navController.navigate(Routes.CartScreen.route)
                        }
                )
            })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = startdestination ){
                composable(Routes.HomeScreen.route){
                    HomeScreen()
                }
                composable(Routes.CartScreen.route){
                    CartScreen()
                }
            }
        }
    }
}