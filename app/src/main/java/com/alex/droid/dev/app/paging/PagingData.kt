@file:Suppress("DataClassPrivateConstructor")

package com.alex.droid.dev.app.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

class PagingData<ListItem>(
    val pagedList: LiveData<PagedList<ListItem>>,
    val loadingState: LiveData<LoadingState>,
    val refreshState: LiveData<LoadingState>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class LoadingState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }
}