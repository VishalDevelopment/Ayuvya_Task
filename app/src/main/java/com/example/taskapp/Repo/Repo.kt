package com.example.taskapp.Repo

import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun InsertDataintoProduct(data: ProductType)
    fun GetDataFromProduct(): Flow<List<ProductType>>
    fun GetTotalCartItem():Flow<Int>

    suspend fun InsertDataintoItem(data: ItemType)
    fun GetDataFromItem(): Flow<List<ItemType>>
}