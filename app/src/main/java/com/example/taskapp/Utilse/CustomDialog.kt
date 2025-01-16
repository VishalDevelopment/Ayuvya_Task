package com.example.taskapp.Utilse

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import com.example.taskapp.R
import com.example.taskapp.ViewModel.TaskViewModel

@Composable
fun CustomDialog(data: ItemType, RemoveDialog: () -> Unit) {

    val product = remember {
        mutableStateOf(data)
    }
    val context = LocalContext.current
    val Unit = remember {
        mutableStateOf(0)
    }
    Log.d("CUSTOMDATA","$product")
    val viewmodel: TaskViewModel = hiltViewModel()
    Dialog(onDismissRequest = {
        RemoveDialog()
    }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Product Detail",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = colorResource(id = R.color.black),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    RemoveDialog()
                                }
                        )
                    }

                    Column(Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = product.value.imageVector),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "${product.value.name}",
                            color = Color.Black,
                            fontSize = 32.sp,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Text(
                            text = "Quantity : ${product.value.quantity}",
                            color = Color.Black,
                            fontSize = 22.sp
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_check_indeterminate_small_24),
                            modifier = Modifier
                                .size(40.dp)
                                .weight(1f)
                                .clickable {
                                    if (Unit.value > 0) {
                                        Unit.value--
                                    }
                                },
                            contentDescription = null
                        )
                        Text(
                            text = Unit.value.toString(),
                            fontSize = 32.sp,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            modifier = Modifier
                                .size(40.dp)
                                .weight(1f)
                                .clickable {
                                    if (Unit.value < product.value.quantity.toInt()) {
                                        Unit.value++
                                    }

                                },
                            contentDescription = null
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if (Unit.value == 0) {
                                    Toast.makeText(context, "Add Some Quantity", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    viewmodel.insertProduct(
                                        ProductType(
                                            product.value.id,
                                            product.value.imageVector,
                                            product.value.name,
                                            Unit.value.toString()
                                        )
                                    )
                                    val updateQty = product.value.quantity.toInt() - Unit.value.toInt()
                                    viewmodel.insertItem(ItemType(
                                        product.value.id,
                                        product.value.imageVector,
                                        product.value.name,
                                        updateQty.toString()))

                                    Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT)
                                        .show()
                                    RemoveDialog()
                                }
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Add to Cart")
                        }
                    }
                }
            }
        }
    }
}