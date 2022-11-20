package com.example.exercise2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class ActivityRight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_right)

        val resultText: TextView = findViewById(R.id.textView2)

        val startForResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK)
                {
                    val intent = result.data // the same: intent = result.getData()
                    val i = intent?.getIntExtra("result",0) //get result data
                    if (i != null) {
                        val str = i.toString()
                        resultText.text = str
                    }
                }
            }

        val et1: EditText = findViewById(R.id.firstNumber)
        val et2: EditText = findViewById(R.id.secondNumber)

        val buttonCalc: Button = findViewById(R.id.button)

        buttonCalc.setOnClickListener { _ ->
            val val1:Int = et1.text.toString().toInt()
            val val2:Int = et2.text.toString().toInt()
            val bundle = Bundle()
            bundle.putInt("val1", val1)
            bundle.putInt("val2",val2)
            val intent = Intent(this, ActivityCalc::class.java)
            intent.putExtras(bundle)
            startForResult.launch(intent)
        }
    }
}