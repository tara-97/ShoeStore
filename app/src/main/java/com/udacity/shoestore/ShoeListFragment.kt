package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.ItemLayoutBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "ShoeListFragment"
class ShoeListFragment : Fragment() {


    private lateinit var binding : ShoeListFragmentBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoes ->
            if(shoes.size > 0){
                addNewShoeView(shoes)
            }

        })
        viewModel.isLogged.observe(viewLifecycleOwner, Observer {
            isUserLoggedIn ->
            if(!isUserLoggedIn){
                findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
            }
        })
        setHasOptionsMenu(true)
        binding.btnNavigate.setOnClickListener({ view ->
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        })
        return binding.root
    }
    private fun addNewShoeView(shoes : MutableList<Shoe>){
        for(shoe in shoes){
            val layout = LinearLayout(requireContext())
            layout.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
            val shoeNameText : TextView = TextView(requireContext())
            shoeNameText.text = shoe.name
            val shoeCompanyText = TextView(requireContext())
            shoeCompanyText.text = shoe.company
            layout.addView(shoeNameText)
            layout.addView(shoeCompanyText)

            binding.scrollViewLinear.addView(layout)

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                viewModel.changeLoginState()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


