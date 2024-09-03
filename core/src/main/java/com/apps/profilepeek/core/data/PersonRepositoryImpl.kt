package com.apps.profilepeek.core.data

import com.apps.profilepeek.core.data.local.LocalDataSource
import com.apps.profilepeek.core.data.local.entity.PersonEntity
import com.apps.profilepeek.core.data.mappers.PersonMappers
import com.apps.profilepeek.core.data.remote.RemoteDataSource
import com.apps.profilepeek.core.data.remote.network.ApiResponse
import com.apps.profilepeek.core.data.remote.response.PersonResponse
import com.apps.profilepeek.core.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): PersonRepository {
    override fun getPersonList(): Flow<Resource<List<PersonEntity>>> =
        object: NetworkBoundResource<List<PersonEntity>, PersonResponse>() {
            override fun loadFromDB(): Flow<List<PersonEntity>> {
                return localDataSource.getAllPersons()
            }

            override suspend fun createCall(): Flow<ApiResponse<PersonResponse>> {
                return remoteDataSource.getPerson()
            }

            override suspend fun saveCallResult(data: PersonResponse) {
                val personList = PersonMappers.mapPersonResponseToEntity(data)
                localDataSource.insertPersons(personList)
            }

            override fun shouldFetch(data: List<PersonEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
}