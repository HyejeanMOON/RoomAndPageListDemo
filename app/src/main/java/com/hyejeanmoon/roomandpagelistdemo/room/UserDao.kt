package com.hyejeanmoon.roomandpagelistdemo.room

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun getAllByLivePage(): DataSource.Factory<Int, User>

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(users: List<User>)

    @Transaction
    suspend fun deleteAllAndInsertUser(users: List<User>) {
        deleteUser(getAll())
        users.forEach { insertUser(it) }
    }

}