package com.example.zooanimalbrowser

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val closeAppButton: Button = findViewById(R.id.closeAppButton)
        closeAppButton.setOnClickListener {
            finish()
        }
    }
}
