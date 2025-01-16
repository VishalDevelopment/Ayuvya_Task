package com.example.taskapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType

@Database(entities = [ProductType::class,ItemType::class], version = 1)
abstract class ProductDB():RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun ItemDao(): ItemDataDao
}

