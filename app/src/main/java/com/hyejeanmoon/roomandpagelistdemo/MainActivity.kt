package com.hyejeanmoon.roomandpagelistdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejeanmoon.roomandpagelistdemo.databinding.ActivityMainBinding
import com.hyejeanmoon.roomandpagelistdemo.room.User
import com.hyejeanmoon.roomandpagelistdemo.room.UserDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnInsert.setOnClickListener {
            launch {
                UserDataBase.getInstance(applicationContext)!!.also { database ->
                    generateUser().forEach { database.getUserDao().insertUser(it) }
                }
            }
        }

        binding.btnDeleteAndInsert.setOnClickListener {
            launch {
                UserDataBase.getInstance(applicationContext)?.also { database ->
                    database.getUserDao().deleteAllAndInsertUser(generateUser())

                }
            }
        }

        binding.btnShow.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, ShowActivity::class.java)
            startActivity(intent)
        }

    }

    private fun generateUser(): List<User> {
        var users: ArrayList<User> = ArrayList()

        val firstNamePrefix = "firstName "
        val lastNamePrefix = "lastName "
        val birthDayPrefix = "birthDay "
        val nationalityPrefix = "nationality "

        for (i in 0 until 1000) {
            val user = User(
                firstName = "$firstNamePrefix$i",
                lastName = "$lastNamePrefix$i",
                birthday = "$birthDayPrefix$i",
                nationality = "$nationalityPrefix$i"
            )
            users.add(
                user
            )

        }
        return users.toList()
    }

}
