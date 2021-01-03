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

        // Build and display a card view for each shoe. Display in reverse order so that new objects appear
        // at the top of the list
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            for (shoe in it) {
                DataBindingUtil.inflate<ListRowBinding>(
                    layoutInflater,
                    R.layout.list_row,
                    binding.shoeListHolder,
                    true
                ).apply {
                    this.shoe = shoe
                }
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