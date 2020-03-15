package com.hyejeanmoon.roomandpagelistdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hyejeanmoon.roomandpagelistdemo.databinding.ActivityShowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ShowActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_show)

        val adapter = UserAdapter()

        val viewModel = ViewModelProviders.of(this).get(ShowViewModel::class.java)

        binding.recyclerView.adapter = adapter


        viewModel.allUsers.observe(this, Observer {
            adapter.submitList(it)
        })

    }

}