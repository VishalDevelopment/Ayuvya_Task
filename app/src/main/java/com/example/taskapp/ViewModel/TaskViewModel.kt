package com.example.taskapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.Model.ItemType
import com.example.taskapp.Model.ProductType
import com.example.taskapp.Repo.RepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repoImpl: RepoImpl):ViewModel() {
    fun insertProduct (product: ProductType){
        viewModelScope.launch {
            repoImpl.InsertDataintoProduct(product)
        }
    }
    fun getAllProduct() : kotlinx.coroutines.flow.Flow<List<ProductType>> = repoImpl.GetDataFromProduct()
    fun getCartCount():Flow<Int> = repoImpl.GetTotalCartItem()

    fun insertItem (product: ItemType){
        viewModelScope.launch {
            repoImpl.InsertDataintoItem(product)
        }
    }
    fun getAllItem() : kotlinx.coroutines.flow.Flow<List<ItemType>> = repoImpl.GetDataFromItem()
}