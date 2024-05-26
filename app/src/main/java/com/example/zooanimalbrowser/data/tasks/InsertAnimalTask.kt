package com.example.zooanimalbrowser.data.tasks

import android.os.AsyncTask
import com.example.zooanimalbrowser.ZooApplication
import com.example.zooanimalbrowser.data.models.AnimalDBModel

class InsertAnimalTask(
    private val app: ZooApplication,
    private val onSuccess: (Boolean) -> Unit
) : AsyncTask<AnimalDBModel, Unit, Boolean>() {
    override fun doInBackground(vararg params: AnimalDBModel?): Boolean {
        var isUpdated = false
        params.getOrNull(0)?.let { animal ->
            val existingAnimal = app.database.animalDao().getAnimalByName(animal.name)
            if (existingAnimal != null) {
                app.database.animalDao().updateAnimal(existingAnimal.id, animal.continent)
                isUpdated = true
            } else {
                app.database.animalDao().insert(animal)
            }
        }
        return isUpdated
    }

    override fun onPostExecute(result: Boolean) {
        onSuccess(result)
    }
}
