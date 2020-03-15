package com.hyejeanmoon.roomandpagelistdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.hyejeanmoon.roomandpagelistdemo.room.UserDataBase

class ShowViewModel(application: Application) : AndroidViewModel(application) {

    val dao =
        UserDataBase.getInstance(
            application.applicationContext
        )?.getUserDao()

    val allUsers = dao!!.getAllByLivePage()
        .toLiveData(Config(pageSize = 10, enablePlaceholders = true, maxSize = 50))

}