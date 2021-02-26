package com.example.productsmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels

class RecycleViewActivity : AppCompatActivity() {

    var selectedId: Int? = null
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory((application as ProductsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)
        //setContentView(R.layout.fragment_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
    fun pasaDato(id: Int){
        productViewModel.selectedId = id
    }
}