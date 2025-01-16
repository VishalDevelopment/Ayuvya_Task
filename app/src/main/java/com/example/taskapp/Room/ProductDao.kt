package com.example.taskapp.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductToCart(product: ProductType)
    @Query("SELECT * FROM product_cart")
    fun getAllProduct(): Flow<List<ProductType>>
    @Query("SELECT COUNT(*) FROM item_data")
    fun getItemCount(): Flow<Int>

}

@Dao
interface ItemDataDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemToDb(product: ItemType)

    @Query("SELECT * FROM item_data")
    fun getAllItem(): Flow<List<ItemType>>


}