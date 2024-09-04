package com.apps.profilepeek.core.domain.usecase

import com.apps.profilepeek.core.data.Resource
import com.apps.profilepeek.core.domain.mapper.DomainDataMapper.mapToUiModel
import com.apps.profilepeek.core.domain.model.PersonUiModel
import com.apps.profilepeek.core.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPersonUseCase @Inject constructor(private val personRepository: PersonRepository) {
    fun execute(): Flow<Resource<List<PersonUiModel>>> {
        return personRepository.getPersonList().map {
            when (it) {
                is Resource.Success -> {
                    val data = it.data ?: emptyList()
                    Resource.Success(mapToUiModel(data))
                }
                is Resource.Error -> {
                    val message = it.message ?: "Unknown error"
                    Resource.Error(message)
                }
                is Resource.Loading -> Resource.Loading()
            }
        }
    }


}