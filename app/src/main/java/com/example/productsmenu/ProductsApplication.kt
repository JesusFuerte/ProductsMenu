package com.example.productsmenu

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ProductsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ProductRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { ProductRepository(database.productDao()) }
}