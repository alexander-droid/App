package com.alex.droid.dev.app.usecase

import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.alex.droid.dev.app.paging.AbsDataSource
import com.alex.droid.dev.app.paging.DataSourceFactory
import com.alex.droid.dev.app.paging.PagingData

abstract class BasePagingUseCase<Action, Data>() : BaseUseCase<Action, PagingData<Action, Data>>() {

    override fun execute(action: Action): PagingData<Action, Data> {

        val dataSourceFactory = object : DataSourceFactory<Action, Data>() {
            override fun provideDataSource(): AbsDataSource<Action, Data> {
                return this@BasePagingUseCase.provideDataSource(action)
            }
        }

        val livePagedList = dataSourceFactory.toLiveData(
            config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(30)
                .setPrefetchDistance(20)
                .build()
        )

        return PagingData(
            pagedList = livePagedList,
            networkState = dataSourceFactory.sourceLiveData.switchMap {
                it.initialLoad
            },
            refreshState = dataSourceFactory.sourceLiveData.switchMap {
                it.networkState
            },
            refresh = {
                dataSourceFactory.sourceLiveData.value?.invalidate()
            },
            retry = {
                dataSourceFactory.sourceLiveData.value?.retry()
            }
        )
    }

    protected abstract fun provideDataSource(action: Action): AbsDataSource<Action, Data>
}