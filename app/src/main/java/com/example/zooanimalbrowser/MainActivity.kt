package com.example.zooanimalbrowser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
                showAlert("Validation Error", "The Animal Name field cannot be empty.")
                return@setOnClickListener
            }
            if (continentName.isEmpty()) {
                showAlert("Validation Error", "The Continent field cannot be empty.")
                return@setOnClickListener
            }
            if (!isValidContinent(continentName)) {
                showAlert("Validation Error", "Invalid Continent.")
                return@setOnClickListener
            }

            val app = application as ZooApplication
            InsertAnimalTask(app) { isUpdated ->
                if (isUpdated) {
                    showAlert("Duplicate avoided", "Animal continent was updated successfully.")
                } else {
                    showAlert("Success", "Animal added successfully.")
                }
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

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}
