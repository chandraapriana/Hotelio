package com.chandra.hotelio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class About : AppCompatActivity() {
    private lateinit var btnBack:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            print(e)
        }

        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}