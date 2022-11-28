package com.sample.emmarsample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.emmarsample.models.datamodels.User

@Dao
interface UsersDao {
    @Insert
    fun insertAll(vararg users: User)

    @Query("SELECT * FROM user WHERE page = :pageNo")
    fun loadAllByIds(pageNo: Int): List<User>
}