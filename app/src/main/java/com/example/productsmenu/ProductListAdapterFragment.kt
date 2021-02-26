package com.example.productsmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ProductListAdapterFragment(
    private val list: List<Product>,
    val activity: RecycleViewActivity
)
    : RecyclerView.Adapter<ProductListAdapterFragment.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private var mItem: Product? = null
        var nombre: TextView
        var miid: TextView
        var position: Int? = null

        init {
            nombre = view.findViewById<View>(R.id.title) as TextView
            miid = view.findViewById(R.id.miid)
            view.setOnClickListener(this);

        }

        fun setItem(product: Product, position: Int) {
            mItem = product
            nombre.text = product.product
            miid.text = product.id.toString()
            this.position = position
        }

        override fun onClick(view: View){
            activity.selectedId = mItem?.id
            (activity as RecycleViewActivity).pasaDato(mItem?.id!!)
            activity.findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_firstFragment_to_detailFragment,)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cliente = list[position]
        holder.setItem(cliente, position)
        /*
        holder.itemView.setOnClickListener{
            Navigation.createNavigateOnClickListener(R.id.action_SecondFragment_to_detailFragment)
        }
         */
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ProductsComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.product == newItem.product
        }
    }
}