package com.apps.profilepeek.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity (
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val city: String = "",
    val avatar: String = "",
)