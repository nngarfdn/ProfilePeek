package com.apps.profilepeek.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.profilepeek.core.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getAllPerson(): Flow<List<PersonEntity>>

    @Query("SELECT * FROM person WHERE id = :id")
    fun getPersonById(id: String): Flow<PersonEntity?>

    @Query("DELETE FROM person")
    fun clearAllPerson()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersons(persons: List<PersonEntity>)

}