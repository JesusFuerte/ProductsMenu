package com.example.productsmenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class DetailFragment : Fragment() {

    var id : Int? = null
    var textlabel: TextView? = null
    var button_edit: Button? = null
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory((activity?.application as ProductsApplication).repository)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //textlabel = view.findViewById(R.id.label_product)
        //button_edit = view.findViewById(R.id.button_edit)
        /*Pantalla de Editar
        button_edit?.setOnClickListener{
            findNavController().navigate(R.id.action_detailFragment_to_editFragment)
        }
        */
        id = (activity as RecycleViewActivity).selectedId
        Log.d("app","Detail Act prop: "+ id)
        productViewModel.getElementById(id!!)
        productViewModel.selectedItem?.observe(requireActivity()){
            Log.d("app", "Observed product: $it")
            textlabel?.text = it?.product
        }

    }


}