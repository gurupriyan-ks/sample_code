package com.sample.emmarsample.models.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val uid: Int = 0,
    //FIXME storing the whole json per page for the sake of simplicity
    @ColumnInfo(name = "user_json")
    val userJson: String?,
    @ColumnInfo(name = "page")
    val page: Int?
)