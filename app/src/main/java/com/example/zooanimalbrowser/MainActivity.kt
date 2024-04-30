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
        val homeButton: Button = findViewById(R.id.homeButton)
        homeButton.setOnClickListener {
            if (!returnHome()) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, AnimalListFragment())
                    .commit()
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AnimalListFragment())
                .commit()
        }
    }
    private fun returnHome(): Boolean {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            val entry = supportFragmentManager.getBackStackEntryAt(i)
            if (entry.name == "AnimalListFragment") {
                supportFragmentManager.popBackStackImmediate("AnimalListFragment", 0)
                return true
            }
        }
        return false
    }
}
