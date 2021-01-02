package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivityViewModel
import com.udacity.shoestore.NavigationDirections
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListRowBinding
import kotlinx.android.synthetic.main.list_row.view.*
import androidx.fragment.app.activityViewModels


class ShoeListFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
           inflater, R.layout.fragment_shoe_list, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            viewModel.shoeList.value?.asReversed()?.map {
                val listItemBinding = DataBindingUtil.inflate<ListRowBinding>(layoutInflater, R.layout.list_row, binding.shoeListHolder, false)
                val listItem = listItemBinding.listItemCard
                listItem.list_item.text = "${it.company} ${it.name}\n${it.description}, Size: ${it.size}"
                binding.shoeListHolder.addView(listItem)
            }
        })

        setHasOptionsMenu(true)

        binding.floatingActionButton.setOnClickListener { view: View -> view.findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToDetailFragment()
        ) }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.options_menu_logout -> {
                findNavController().navigate(NavigationDirections.actionGlobalLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}