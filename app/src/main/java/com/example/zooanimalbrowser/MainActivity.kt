package com.example.zooanimalbrowser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zooanimalbrowser.data.models.AnimalDBModel
import com.example.zooanimalbrowser.data.tasks.InsertAnimalTask

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

        val nameOfAnAnimal: EditText = findViewById(R.id.nameOfAnAnimal)
        val continent: EditText = findViewById(R.id.continent)
        val addButton: Button = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val name = nameOfAnAnimal.text.toString().trim()
            val continentName = continent.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "The Animal Name field cannot be empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (continentName.isEmpty()) {
                Toast.makeText(this, "The Continent field cannot be empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isValidContinent(continentName)) {
                Toast.makeText(this, "Invalid Continent.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val app = application as ZooApplication
            InsertAnimalTask(app) {
                (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? AnimalListFragment)?.updateAnimalList()
            }.execute(AnimalDBModel(name = name, continent = continentName))
        }
    }

    private fun isValidContinent(continent: String): Boolean {
        val validContinents = listOf("Europe", "Africa", "Asia", "North America", "South America", "Australia", "Antarctica")
        return validContinents.contains(continent)
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
