package com.example.zooanimalbrowser.data.tasks

import android.os.AsyncTask
import com.example.zooanimalbrowser.ZooApplication
import com.example.zooanimalbrowser.data.models.AnimalDBModel

class GetAllAnimalsTask(
    private val app: ZooApplication,
    private val onSuccess: (List<AnimalDBModel>) -> Unit
) : AsyncTask<Unit, Unit, List<AnimalDBModel>>() {
    override fun doInBackground(vararg params: Unit?): List<AnimalDBModel> {
        return app.database.animalDao().getAllAnimals()
    }

    override fun onPostExecute(result: List<AnimalDBModel>?) {
        result?.let { onSuccess(it) }
    }
}
