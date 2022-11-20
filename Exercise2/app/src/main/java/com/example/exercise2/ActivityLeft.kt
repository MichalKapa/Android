package com.example.exercise2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity

class ActivityLeft : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_left)

        val phoneNumber: EditText = findViewById(R.id.editDialer)
        val browserAddress: EditText = findViewById(R.id.editBrowser)
        val sms: EditText = findViewById(R.id.editSms)

        val runDialButton: Button = findViewById(R.id.dialerButton)
        runDialButton.setOnClickListener {
            val phoneNumberText = phoneNumber.text.toString()
            runDial(phoneNumberText)
        }

        val runSMSButton: Button = findViewById(R.id.sms_button)
        runSMSButton.setOnClickListener {
            val SMS_URI = Uri.parse("smsto:123123123")
            val smsText = sms.text.toString()
            runSms(SMS_URI, smsText)
        }

        val runBrowserButton: Button = findViewById(R.id.browserButton)
        runBrowserButton.setOnClickListener {
            val browserAdressText = browserAddress.text.toString()
            runBrowser(browserAdressText)
        }
    }

    fun runDial(phoneNumberText: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumberText ")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun runSms(SMS_URI: Uri, smsText: String) {
        val intent = Intent(Intent.ACTION_SENDTO, SMS_URI)
        intent.putExtra("sms_body", smsText)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun runBrowser(browserAddressText: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(browserAddressText))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}