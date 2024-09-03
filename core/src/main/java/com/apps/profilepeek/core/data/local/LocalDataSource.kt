package com.apps.profilepeek.core.data.local

import com.apps.profilepeek.core.data.local.entity.PersonEntity
import com.apps.profilepeek.core.data.local.room.PersonDao
import com.apps.profilepeek.core.data.remote.response.PersonResponse
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: PersonDao) {
    fun getAllPersons(): Flow<List<PersonEntity>> = dao.getAllPerson()
    fun getPersonById(id: String): Flow<PersonEntity?> = dao.getPersonById(id)
    fun insertPersons(persons: List<PersonEntity>) = dao.insertPersons(persons)
    fun clearAllPersons() = dao.clearAllPerson()
}