package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel : ViewModel() {

    // list of all shoe inventory items
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    // flag that tracks whether a shoe has been added
    // necessary to avoid referencing fragments in the view model (to avoid memory leaks)
    private val _eventShoeAdded = MutableLiveData<Boolean>()
    val eventShoeAdded: LiveData<Boolean>
        get() = _eventShoeAdded

    init {
        _eventShoeAdded.value = false
        //Initialize shoe list with dummy values
        _shoeList.value = mutableListOf(
            Shoe("Cloudfoam Runner", 8.5, "Adidas", "Easy, everyday runners"),
            Shoe("Classic Clog", 5.0, "Crocs", "Unisex iconic clog"),
            Shoe("Rain Boot", 6.0, "Hunter", "Womens Short Navy Matte Boot"))
    }

    fun addNewShoe(shoe: Shoe) {

        // Replace empty values with dashes (text) or 0.0 (size) if the user leaves values blank
        val inputName = if (shoe.name == "") "-" else shoe.name
        val inputSize: Double = if (shoe.size.toString() == "") 0.0 else shoe.size.toString().toDouble()
        val inputCompany = if (shoe.company == "") "-" else shoe.company
        val inputDescription = if (shoe.description == "") "-" else shoe.description

        // Add shoe and set the flag to true to trigger observer on shoe list screen
        _shoeList.value?.add(Shoe(inputName, inputSize, inputCompany, inputDescription))
        _eventShoeAdded.value = true
    }

    fun shoeAddedComplete() {
        _eventShoeAdded.value = false
    }

}
