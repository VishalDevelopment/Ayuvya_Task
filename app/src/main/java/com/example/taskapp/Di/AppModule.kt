package com.example.taskapp.Di

import android.content.ClipData.Item
import dagger.Module
import android.content.Context
import androidx.room.Room
import com.example.taskapp.Room.ItemDataDao
import com.example.taskapp.Room.ProductDB
import com.example.taskapp.Room.ProductDao
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun ProductDbRef(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ProductDB::class.java,
        "product"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun ProductDaoRef(db: ProductDB) = db.productDao()

    @Singleton
    @Provides
    fun ItemDaoRef(db: ProductDB) = db.ItemDao()


}