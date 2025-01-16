package com.example.taskapp.Screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import com.example.taskapp.R
import com.example.taskapp.Utilse.CustomDialog
import com.example.taskapp.ViewModel.TaskViewModel

@Composable
fun HomeScreen() {
    val viewmodel : TaskViewModel = hiltViewModel()


    val data = viewmodel.getAllItem().collectAsState(initial = emptyList())
    LaunchedEffect(data.value) {
        Log.d("HOMEDATA", "Data fetched: ${data.value}")
    }

    val showDialog = remember { mutableStateOf(false) }
    val selectedProduct = remember { mutableStateOf<ItemType?>(null) }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(data.value) { product ->
            ItemView(product) { clickedProduct ->
                selectedProduct.value = clickedProduct
                showDialog.value = true
            }
        }
    }

    if (showDialog.value && selectedProduct.value != null) {
        CustomDialog(data = selectedProduct.value!!) {
            showDialog.value = false
        }
    }
}

@Composable
fun ItemView(product: ItemType, onAddToCartClick: (ItemType) -> Unit) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(0.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageVector),
                modifier = Modifier.size(150.dp),
                contentDescription = null
            )

            Text(
                text = "${product.name}",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 3.dp),
                fontWeight = FontWeight.Medium
            )

            Button(
                onClick = {
                    onAddToCartClick(product)
                },
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .padding(top = 3.dp, start = 10.dp, end = 10.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(0.5.dp, Color.Black)
            ) {
                Text(
                    text = "Add to cart",
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}