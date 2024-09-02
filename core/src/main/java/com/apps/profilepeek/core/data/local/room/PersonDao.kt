package com.apps.profilepeek.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.profilepeek.core.data.local.entity.PersonEntity

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    suspend fun getAllPerson(): List<PersonEntity>

    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPersonById(id: String): PersonEntity?

    @Query("DELETE FROM person")
    suspend fun clearAllPerson()

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

}