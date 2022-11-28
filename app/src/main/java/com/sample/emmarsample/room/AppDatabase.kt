package com.sample.emmarsample.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.emmarsample.models.datamodels.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao
}