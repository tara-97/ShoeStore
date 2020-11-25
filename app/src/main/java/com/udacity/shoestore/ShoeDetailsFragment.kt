package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.models.Shoe


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "ShoeDetailsFragment"
class ShoeDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding:ShoeDetailsFragmentBinding
    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var shoeNameText: TextView
    private lateinit var shoeCompanyText: TextView
    private lateinit var shoeSizeText:TextView
    private lateinit var shoeDescText:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.shoe_details_fragment,container,false)
        shoeNameText = binding.etShoeName
        shoeCompanyText = binding.etShoeCompany
        shoeSizeText = binding.etShoeSize
        shoeDescText = binding.etShoeDesc
        binding.btnSave.setOnClickListener({
            view ->  addShoe()
        })
        return binding.root

    }
    private fun addShoe(){
        Log.d(TAG, "addShoe: Called")
        val shoeName = shoeNameText.text.toString()
        val shoeSize = shoeSizeText.text.toString().toDouble()
        val shoeCompany = shoeCompanyText.text.toString()
        val shoeDesc = shoeDescText.text.toString()
        val newShoe : Shoe = Shoe(shoeName,shoeSize,shoeCompany,shoeDesc)
        binding.shoe = newShoe
        viewModel.addShoe(newShoe)
    }
}