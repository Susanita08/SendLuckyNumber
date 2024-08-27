package com.curso.android.luckynumber

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val welcomeText : TextView = findViewById(R.id.welcome_text)
        val luckyNumber : TextView = findViewById(R.id.lucky_number)
        val shareButton : Button = findViewById(R.id.share_btn)

        val intent = intent

        val userName : String = intent . getStringExtra ("userName").toString()

        val luckyNumberValue = generateRandomNumber()
        luckyNumber.text = "$luckyNumberValue"


        shareButton.setOnClickListener {
            shareData(userName, luckyNumberValue)
            val userName = intent.getStringExtra("userName")
            welcomeText.text = "Welcome $userName"
            luckyNumber.text = "Your lucky number is ${(0..100).random()}"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun generateRandomNumber() : Int {
        return (0..1000).random()
    }

    fun shareData(userName: String, luckyNumber: Int) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello $userName, got lucky today")
        intent.putExtra(Intent.EXTRA_TEXT, "Your lucky number is $luckyNumber")
        startActivity(Intent.createChooser(intent, "Choose a platform"))
    }
}