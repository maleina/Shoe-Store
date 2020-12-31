package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel : ViewModel() {

//    private val _name = MutableLiveData<String>()
//    private val _size = MutableLiveData<Double>()
//    private val _company = MutableLiveData<String>()
//    private val _description = MutableLiveData<String>()

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        // Initialize shoe list with dummy values
        _shoeList.value = mutableListOf(
            Shoe("Cloudfoam Runner", 8.5, "Adidas", "Easy, everyday runners"),
            Shoe("Classic Clog", 5.0, "Crocs", "Unisex iconic clog"),
            Shoe("Rain Boot", 6.0, "Hunter", "Womens Short Navy Matte Boot"))

    }

}
