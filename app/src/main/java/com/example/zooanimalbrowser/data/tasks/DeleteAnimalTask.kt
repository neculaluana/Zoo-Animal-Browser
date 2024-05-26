package com.example.zooanimalbrowser.data.tasks

import android.os.AsyncTask
import com.example.zooanimalbrowser.ZooApplication
import com.example.zooanimalbrowser.data.models.AnimalDBModel

class DeleteAnimalTask(
    private val app: ZooApplication,
    private val onSuccess: () -> Unit
) : AsyncTask<AnimalDBModel, Unit, Unit>() {
    override fun doInBackground(vararg params: AnimalDBModel?) {
        params.getOrNull(0)?.let { animal ->
            app.database.animalDao().deleteAnimalById(animal.id)
        }
    }

    override fun onPostExecute(result: Unit?) {
        onSuccess()
    }
}
