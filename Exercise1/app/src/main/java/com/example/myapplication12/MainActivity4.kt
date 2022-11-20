package com.example.myapplication12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity4 : AppCompatActivity(), View.OnLongClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val act4: View = findViewById(R.id.activity4)
        act4.setOnLongClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onLongClick(p0: View?): Boolean {
        onBackPressed()
        return true
    }

    fun finishActivity4(view: View?) {
        finish()
    }
}