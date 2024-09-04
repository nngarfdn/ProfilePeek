package com.apps.profilepeek.core.domain.repository

import com.apps.profilepeek.core.data.Resource
import com.apps.profilepeek.core.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPersonList(): Flow<Resource<List<PersonEntity>>>
    fun getPersonDetail(id: String): Flow<PersonEntity?>
}