package com.example.taskapp.Repo

import android.util.Log
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import com.example.taskapp.Room.ItemDataDao
import com.example.taskapp.Room.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val productdb: ProductDao,
    private val itemDB: ItemDataDao
) : Repo {

    override  suspend fun InsertDataintoProduct(data: ProductType) {
        productdb.insertProductToCart(data)
    }
    override fun GetDataFromProduct(): Flow<List<ProductType>> = productdb.getAllProduct()
    override fun GetTotalCartItem(): Flow<Int> {
      return  productdb.getItemCount()
    }


    override  suspend fun InsertDataintoItem(data: ItemType) {

        itemDB.insertItemToDb(data)
    }
    override fun GetDataFromItem(): Flow<List<ItemType>> {
        return  itemDB.getAllItem()
    }

}