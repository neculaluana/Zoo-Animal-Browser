// AnimalDao.kt
package com.example.zooanimalbrowser.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zooanimalbrowser.data.models.AnimalDBModel
import com.example.zooanimalbrowser.data.models.ContinentDBModel

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animal: AnimalDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContinent(continent: ContinentDBModel)

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): List<AnimalDBModel>

    @Query("DELETE FROM animals WHERE id = :animalId")
    fun deleteAnimalById(animalId: Int)

    @Query("SELECT * FROM continents WHERE name = :continentName")
    fun getContinentByName(continentName: String): ContinentDBModel?

    @Query("SELECT * FROM animals WHERE LOWER(name) = LOWER(:name) LIMIT 1")
    fun getAnimalByName(name: String): AnimalDBModel?

    @Query("UPDATE animals SET continent = :continent WHERE id = :id")
    fun updateAnimal(id: Int, continent: String)
}
