package com.example.taskapp.Model

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_cart")
data class ProductType(
    @PrimaryKey (autoGenerate = true)
    val id:Int,
    val imageVector: Int,
    val name :String,
    val quantity : String
)


@Entity(tableName = "item_data")
data class ItemType(
    @PrimaryKey (autoGenerate = true)
    val id:Int,
    val imageVector: Int,
    val name :String,
    val quantity : String
)




