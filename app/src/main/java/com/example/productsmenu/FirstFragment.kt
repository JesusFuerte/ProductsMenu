package com.example.productsmenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FirstFragment : Fragment() {

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as ProductsApplication).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

         */
        val recyclerView =  view.findViewById<RecyclerView>(R.id.recyclerview)
        var adapter = ProductListAdapterFragment(list = arrayListOf(), activity = activity as RecycleViewActivity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!)

        productViewModel.allProducts.observe(activity!!, Observer { products ->
            // Update the cached copy of the words in the adapter.
            adapter = ProductListAdapterFragment(list = products, activity = activity as RecycleViewActivity)
            recyclerView.adapter = adapter
        })

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        //val intent = Intent(this as Context,RecycleViewActivity::class.java)
        /*
        fab.setOnClickListener  {
            val intent = Intent(this as Context, RecycleViewActivity::class.java)
            startActivity(intent)
        }*/
    }
}