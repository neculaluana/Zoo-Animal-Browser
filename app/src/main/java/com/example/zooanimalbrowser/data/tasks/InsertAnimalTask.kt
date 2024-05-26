package com.example.zooanimalbrowser.data.tasks

import android.os.AsyncTask
import com.example.zooanimalbrowser.ZooApplication
import com.example.zooanimalbrowser.data.models.AnimalDBModel

class InsertAnimalTask(
    private val app: ZooApplication,
    private val onSuccess: () -> Unit
) : AsyncTask<AnimalDBModel, Unit, Unit>() {
    override fun doInBackground(vararg params: AnimalDBModel?) {
        params.getOrNull(0)?.let { animal ->
            val existingAnimal = app.database.animalDao().getAnimalByName(animal.name)
            if (existingAnimal != null) {
                app.database.animalDao().updateAnimal(existingAnimal.id, animal.continent)
            } else {
                app.database.animalDao().insert(animal)
            }
        }
    }

    override fun onPostExecute(result: Unit?) {
        onSuccess()
    }
}
