package com.example.productsmenu

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: Flow<List<Product>> = productDao.getAlphabetizedProducts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(product: Product) {
        productDao.insert(product)
    }

    fun findById(id: Int): Flow<Product> {
        return productDao.getProductById(id)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(product:Product) {
        productDao.updateProduct(product)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(product:Product) {
        productDao.deleteProduct(product)
    }
}