package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set up navigation
        navController = this.findNavController(R.id.myNavHostFragment)
        // Pass in the login and shoe list fragments as top level so that
        // the back button will not be enabled on them
        val topLevelFragments = setOf(R.id.loginFragment, R.id.shoeListFragment)
        val appBarConfiguration = AppBarConfiguration(topLevelFragments)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
