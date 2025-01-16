package com.example.taskapp.Screens


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskapp.Model.ProductType
import com.example.taskapp.R
import com.example.taskapp.ViewModel.TaskViewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun CartScreen() {
    val viewmodel: TaskViewModel = hiltViewModel()
    val data = viewmodel.getAllProduct().collectAsState(initial = emptyList())
    Log.d("CARTSCREEN","${data.value}")

    val checkProducts = derivedStateOf { data.value.isNotEmpty() }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if (checkProducts.value == true) {
            Log.d("CARTSCREENLIST","${data.value}")


            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                LazyColumn(modifier = Modifier.padding(vertical = 15.dp)) {
                    items(data.value) {
                        ListView(product = it)
                        Spacer(modifier = Modifier.padding(vertical = 5.dp))
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(.15f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current

            }
        }

        if (checkProducts.value ==false) {
            Log.d("CARTSCREENLISTEMPTY","${data.value}")

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.baseline_shopping_cart_24), contentDescription = null, modifier = Modifier.size(150.dp))
                Text(text = "Empty Cart")
            }
        }
    }
}


@Composable
fun Show_Ui(){
    ListView(product = ProductType(1,R.drawable.product1, "Coffee", "5"))
}



@Composable
fun ListView(product: ProductType) {
    Card(elevation = CardDefaults.elevatedCardElevation(2.dp), border = BorderStroke(1.dp,Color.Black), modifier = Modifier.padding(5.dp), colors = CardDefaults.cardColors(Color.White)){
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth(.95f)
                .border(0.5.dp, Color.Black, shape = RectangleShape)
                .padding(vertical = 8.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = product.imageVector),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)

                    )

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Category :  ${product.name}", fontSize = 20.sp)
                        Text(text = "Unit : ${product.quantity}", fontSize = 20.sp)
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Remove from Cart")
                    }
                }

            }
        }
    }
}