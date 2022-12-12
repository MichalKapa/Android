package com.example.exercise3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//    var frag1: Fragment1? = null //StaticFragment.OnSelectListener
//    var frag2: Fragment2? = null
//    var myTrans: FragmentTransaction? = null
//    private val TAG_F1 = "Fragment1"
//    private val TAG_F2 = "Fragment2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        val navController = navHostFragment.navController
        val bnNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val appBarConfig : AppBarConfiguration =
            AppBarConfiguration(setOf(R.id.fragmentLeft,R.id.fragmentCenter,R.id.fragmentRight, R.id.fragmentX))
        setupActionBarWithNavController(navController,appBarConfig)
        bnNavView.setupWithNavController(navController)

//        bnNavView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.fragmentLeft -> navController.navigate(R.id.action_global_to_fragLeft)
//                R.id.fragmentCenter -> navController.navigate(R.id.action_global_to_fragCenter)
//                R.id.fragmentRight -> navController.navigate(R.id.action_global_to_fragRight)
//            }
//            true
//        }

//        if (savedInstanceState == null) {
//            frag1 = Fragment1.newInstance("a", "b")
//            frag2 = Fragment2.newInstance("a", "b")
//            myTrans = supportFragmentManager.beginTransaction()
//            myTrans!!.add(R.id. fragmentContainer, frag1!!, this.TAG_F1)
//            myTrans!!.detach(frag1!!)
//            myTrans!!.add(R.id. fragmentContainer, frag2!!, this.TAG_F2)
//            myTrans!!.detach(frag2!!)
//            myTrans!!.commit()
//        } else {
//            frag1 = supportFragmentManager.findFragmentByTag(this.TAG_F1) as Fragment1?
//            frag2 = supportFragmentManager.findFragmentByTag(this.TAG_F2) as Fragment2?
//        }

    }

    override fun onMyEvent() {

    }

//    override fun onSelect(option: Int) {
//        val myTrans = supportFragmentManager.beginTransaction()
//        val radioGroup : RadioGroup = findViewById(R.id.radioGroup)
//        val checkedId = radioGroup.checkedRadioButtonId
//        when (checkedId) {
//            R.id.radioButton1 -> {
//                myTrans.detach(frag2!!)
//                myTrans.attach(frag1!!)
//
//            }
//            R.id.radioButton2 -> {
//                myTrans.detach(frag1!!)
//                myTrans.attach(frag2!!)
//            }
//        }
//        myTrans.commit();
//    }
}