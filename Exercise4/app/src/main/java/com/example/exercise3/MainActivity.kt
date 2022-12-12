package com.example.exercise3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), Fragment1.OnMyEventListener, Fragment2.OnMyEventListener{

    private var chosenTheme = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data : SharedPreferences = getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        chosenTheme = data.getInt("chosenTheme", 0)
        when(chosenTheme) {
            0 -> {this.setTheme(R.style.Theme_Exercise3)}
            1 -> {this.setTheme(R.style.Theme_Exercise3_2)}
            2 -> {this.setTheme(R.style.Theme_Exercise3_3)}
        }

        setContentView(R.layout.activity_main)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        val navController = navHostFragment.navController
        val bnNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val appBarConfig : AppBarConfiguration =
            AppBarConfiguration(setOf(R.id.fragmentLeft,R.id.fragmentCenter,R.id.fragmentRight, R.id.fragmentX))
        setupActionBarWithNavController(navController,appBarConfig)
        bnNavView.setupWithNavController(navController)
    }

    override fun onMyEvent() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pozycja1 -> {
                setTheme(R.style.Theme_Exercise3)
                theme.applyStyle(R.style.Theme_Exercise3, true)
                chosenTheme = 0
            }
            R.id.pozycja2 -> {
                setTheme(R.style.Theme_Exercise3_2)
                theme.applyStyle(R.style.Theme_Exercise3_2, true)
                chosenTheme = 1
            }
            R.id.pozycja3 -> {
                setTheme(R.style.Theme_Exercise3_3)
                theme.applyStyle(R.style.Theme_Exercise3_3, true)
                chosenTheme = 2
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
        val data: SharedPreferences = getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putInt("chosenTheme", chosenTheme)
        editor.apply()
        super.onContextItemSelected(item)
        recreate()
        return false
    }
}