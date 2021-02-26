package com.example.productsmenu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory((application as ProductsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProductListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        productViewModel.allProducts.observe(this, Observer { product ->
            product?.let { adapter.submitList(it) }
        })
        //val intent = Intent(this,RecycleViewActivity::class.java)
        //startActivity(intent)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener  {
            val intent = Intent(this as Context, RecycleViewActivity::class.java)
            startActivity(intent)
        }
    }
}