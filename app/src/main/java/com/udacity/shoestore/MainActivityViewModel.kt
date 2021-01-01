package com.udacity.shoestore

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList


    init {
         //Initialize shoe list with dummy values
        _shoeList.value = mutableListOf(
            Shoe("Cloudfoam Runner", 8.5, "Adidas", "Easy, everyday runners"),
            Shoe("Classic Clog", 5.0, "Crocs", "Unisex iconic clog"),
            Shoe("Rain Boot", 6.0, "Hunter", "Womens Short Navy Matte Boot"))
    }

    fun addNewShoe(view: View, shoe: Shoe) {

        // Not real validation, but replace black values with dashes
        val inputName = if (shoe.name == "") "-" else shoe.name
        val inputSize: Double = if (shoe.size.toString() == "") 0.0 else shoe.size.toString().toDouble()
        val inputCompany = if (shoe.company == "") "-" else shoe.company
        val inputDescription = if (shoe.description == "") "-" else shoe.description

        _shoeList.value?.add(Shoe(inputName, inputSize, inputCompany, inputDescription))
        view.findNavController().popBackStack()
    }

}
