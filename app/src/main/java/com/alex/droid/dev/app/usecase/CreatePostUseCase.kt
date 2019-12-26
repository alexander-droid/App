package com.alex.droid.dev.app.usecase

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.api.request.RequestCreatePost
import timber.log.Timber

class CreatePostUseCase(private val feedApi: FeedApi): BaseUseCase<RequestCreatePost, Unit> {

    override suspend fun run(action: RequestCreatePost) {
        Timber.tag("MyCorTest").d("run")
        feedApi.test()
        Timber.tag("MyCorTest").d("run success")
    }
}