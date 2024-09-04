package com.apps.profilepeek.core.data.local

import com.apps.profilepeek.core.data.local.entity.PersonEntity
import com.apps.profilepeek.core.data.local.room.PersonDao
import com.apps.profilepeek.core.data.remote.response.PersonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: PersonDao) {
    fun getAllPersons(): Flow<List<PersonEntity>> = dao.getAllPerson().flowOn(Dispatchers.IO)
    fun getPersonById(id: String): Flow<PersonEntity?> = dao.getPersonById(id).flowOn(Dispatchers.IO)
    suspend fun insertPersons(persons: List<PersonEntity>) = withContext(Dispatchers.IO) {
        dao.insertPersons(persons)
    }

    suspend fun clearAllPersons() = withContext(Dispatchers.IO) {
        dao.clearAllPerson()
    }
}