package com.alex.droid.dev.app.repository

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.db.FeedDao
import com.alex.droid.dev.app.model.actions.ActionFeed
import com.alex.droid.dev.app.model.entity.PostData
import com.alex.droid.dev.app.paging.AbsDataSource
import com.alex.droid.dev.app.usecase.BasePagingUseCase

class FeedUseCase(
    private val feedApi: FeedApi,
    private val feedDao: FeedDao
) : BasePagingUseCase<ActionFeed, PostData>() {

    override fun provideDataSource(action: ActionFeed) = object : AbsDataSource<ActionFeed, PostData>() {
        override fun onLoad(): List<PostData> {
            return feedDao.postsByPage()
        }
    }
}