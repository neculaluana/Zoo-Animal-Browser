package com.example.zooanimalbrowser

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.zooanimalbrowser.data.models.AnimalDBModel
import com.example.zooanimalbrowser.data.tasks.InsertAnimalTask

class MainActivity : AppCompatActivity() {

    private lateinit var nameOfAnAnimal: EditText
    private lateinit var continent: EditText
    private lateinit var addButton: Button

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

        nameOfAnAnimal = findViewById(R.id.nameOfAnAnimal)
        continent = findViewById(R.id.continent)
        addButton = findViewById(R.id.addButton)

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
                showAlert("Validation Error", "Invalid Continent. Choose from \"Europe\", \"Africa\", \"Asia\", \"North America\", \"South America\", \"Australia\" and \"Antarctica\".")
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
                clearFieldsAndHideKeyboard()
            }.execute(AnimalDBModel(name = name, continent = continentName))
        }
    }
    private fun clearFieldsAndHideKeyboard() {
        nameOfAnAnimal.text.clear()
        continent.text.clear()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        nameOfAnAnimal.clearFocus()
        continent.clearFocus()
    }
    fun showInputFields(visible: Boolean) {
        val visibility = if (visible) View.VISIBLE else View.GONE
        nameOfAnAnimal.visibility = visibility
        continent.visibility = visibility
        addButton.visibility = visibility
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
