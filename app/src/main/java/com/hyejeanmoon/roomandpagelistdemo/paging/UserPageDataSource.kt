package com.hyejeanmoon.roomandpagelistdemo.paging

import androidx.paging.PageKeyedDataSource
import com.hyejeanmoon.roomandpagelistdemo.room.User

class UserPageDataSource() : PageKeyedDataSource<Int, User>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {

    }
}