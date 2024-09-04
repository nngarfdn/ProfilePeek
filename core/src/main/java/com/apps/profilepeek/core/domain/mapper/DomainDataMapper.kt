package com.apps.profilepeek.core.domain.mapper

import com.apps.profilepeek.core.data.local.entity.PersonEntity
import com.apps.profilepeek.core.domain.model.PersonUiModel

object DomainDataMapper {
    fun mapToUiModel(data: List<PersonEntity>): List<PersonUiModel> {
        return data.map {
            PersonUiModel(
                id = it.id,
                name = it.name,
                city = it.city,
                avatar = it.avatar
            )
        }
    }

    fun mapToUiModel(data: PersonEntity): PersonUiModel {
        return PersonUiModel(
            id = data.id,
            name = data.name,
            city = data.city,
            avatar = data.avatar
        )
    }
}