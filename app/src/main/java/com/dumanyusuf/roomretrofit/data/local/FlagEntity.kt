package com.dumanyusuf.roomretrofit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flags")
data class FlagEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val capital: String,
    val region: String,
    val currency: String,
    val flag: String,
    val language: String
)
