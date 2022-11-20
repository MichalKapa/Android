package com.example.myapplication12

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.ToggleButton

class MainActivity2 : AppCompatActivity(), View.OnLongClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val act2: View = findViewById(R.id.activity2)
        act2.setOnLongClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageView: ImageView = findViewById(R.id.imageView)

        var switch: Boolean = true

        var isImageSwitch: Boolean = true

        val textView : TextView = findViewById(R.id.textView3)

        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)
        toggleButton.setOnClickListener { view ->
            if (isImageSwitch) {
                if (switch) {
                    imageView.setImageResource(R.drawable.pokemon_angry)
                } else {
                    imageView.setImageResource(R.drawable.pokemon)
                }
                switch = !switch
            }
            else {
                if (switch) {
                    textView.text = "xyz abc"
                }
                else {
                    textView.text = "abc xyz"
                }
                switch =!switch
            }
        }

        val radioButton1: RadioButton = findViewById(R.id.radioButton)
        val radioButton2: RadioButton = findViewById(R.id.radioButton2)

        radioButton1.setOnClickListener { view ->
            isImageSwitch = true
            act2.setBackgroundColor(Color.LTGRAY)

        }

        radioButton2.setOnClickListener { view ->
            isImageSwitch = false
            act2.setBackgroundColor(Color.WHITE)
        }

        val checkbox: CheckBox = findViewById(R.id.checkBox)
        checkbox.setOnClickListener { view ->
            if (checkbox.isChecked) {
                textView.setTypeface(textView.typeface, Typeface.BOLD)
            }
            else {
                textView.setTypeface(textView.typeface, Typeface.NORMAL)
            }
        }
    }

    override fun onLongClick(p0: View?): Boolean {
        onBackPressed()
        return true
    }

    fun finishActivity2(view: View?) {
        finish()
    }
}