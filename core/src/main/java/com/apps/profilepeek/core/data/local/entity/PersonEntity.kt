package com.apps.profilepeek.core.data.local.entity

import androidx.room.Entity

@Entity(tableName = "person")
data class PersonEntity (
    val id: String = "",
    val name: String = "",
    val city: String = "",
    val avatar: String = "",
)