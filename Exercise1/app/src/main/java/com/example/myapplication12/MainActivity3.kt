package com.example.myapplication12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity3 : AppCompatActivity(), View.OnLongClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val act3: View = findViewById(R.id.activity3)
        act3.setOnLongClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onLongClick(p0: View?): Boolean {
        onBackPressed()
        return true
    }

    fun finishActivity3(view: View?) {
        finish()
    }
}