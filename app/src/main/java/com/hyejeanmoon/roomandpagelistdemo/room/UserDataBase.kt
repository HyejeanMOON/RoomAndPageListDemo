package com.hyejeanmoon.roomandpagelistdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class])
abstract class UserDataBase : RoomDatabase() {

    companion object {
        private var INSTANCE: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    UserDataBase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        const val DATABASE_NAME = "user_database.db"
    }

    abstract fun getUserDao(): UserDao
}