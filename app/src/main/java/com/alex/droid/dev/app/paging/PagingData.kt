package com.alex.droid.dev.app.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

class PagingData<Action, ListItem>(
    private val refresh: (action: Action?) -> Unit,
    val pagedList: LiveData<PagedList<ListItem>>,
    val networkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val retry: () -> Unit
) {

    fun refresh(action: Action? = null) {
        refresh.invoke(action)
    }
}