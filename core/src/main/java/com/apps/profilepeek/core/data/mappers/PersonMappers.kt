package com.apps.profilepeek.core.data.mappers

import com.apps.profilepeek.core.data.local.entity.PersonEntity
import com.apps.profilepeek.core.data.remote.response.PersonResponse

object PersonMappers {

    fun mapPersonResponseToEntity(data: PersonResponse): List<PersonEntity>{
        val personList = ArrayList<PersonEntity>()

        data.forEach { personResponse ->
            val person = PersonEntity(
                id = personResponse.id,
                name = personResponse.name,
                city = personResponse.city,
                avatar = personResponse.avatar)
            personList.add(person)
            }
        return personList
    }
}