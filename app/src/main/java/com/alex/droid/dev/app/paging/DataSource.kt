package com.alex.droid.dev.app.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

abstract class AbsDataSource<Action, Data>(): PageKeyedDataSource<String, Data>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private var retryAction: (() -> Any)? = null

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Data>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Data>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Data>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun retry() {

    }




    abstract fun onLoad(): List<Data>
}

abstract class DataSourceFactory<Action, Data>: DataSource.Factory<String, Data>() {

    val sourceLiveData = MutableLiveData<AbsDataSource<Action, Data>>()

    override fun create(): DataSource<String, Data> {
        return provideDataSource().apply {
            sourceLiveData.value = this
        }
    }

    abstract fun provideDataSource(): AbsDataSource<Action, Data>
}