package com.apps.profilepeek.core.domain.usecase

import com.apps.profilepeek.core.data.Resource
import com.apps.profilepeek.core.data.local.entity.PersonEntity
import com.apps.profilepeek.core.domain.model.PersonUiModel
import com.apps.profilepeek.core.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailPersonUseCase @Inject constructor(private val personRepository: PersonRepository) {
    fun execute(id: String): Flow<Resource<PersonUiModel>> {
        return personRepository.getPersonDetail(id).map {
            when (it) {
                is PersonEntity -> {
                    Resource.Success(mapToUiModel(it))
                }
                else -> {
                    Resource.Error("Unknown error")
                }
            }
        }
    }

    private fun mapToUiModel(personEntity: PersonEntity): PersonUiModel {
        return PersonUiModel(
            id = personEntity.id,
            name = personEntity.name,
            avatar = personEntity.avatar,
            city = personEntity.city
        )
    }
}