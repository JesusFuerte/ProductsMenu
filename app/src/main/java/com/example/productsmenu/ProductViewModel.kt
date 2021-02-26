package com.example.productsmenu

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    var selectedId : Int? = null
    var selectedItem: LiveData<Product>? = null
    val allProducts: LiveData<List<Product>> = repository.allProducts.asLiveData()

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun getElementById(id: Int) = viewModelScope.launch {
        selectedItem = repository.findById(id).asLiveData()
    }
    fun updateElement(product: Product) = viewModelScope.launch {
        repository.update(product)
    }
    fun deleteElement(product: Product) = viewModelScope.launch {
        repository.delete(product)
    }
}

class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}