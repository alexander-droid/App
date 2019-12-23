package com.alex.droid.dev.app.usecase

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.api.request.RequestCreatePost
import kotlinx.coroutines.*
import timber.log.Timber

class CreatePostUseCase(private val feedApi: FeedApi): BaseUseCase<RequestCreatePost, Unit> {

    override suspend fun async(action: RequestCreatePost): Deferred<Unit> {
        return feedApi.createPostAsync(action)
    }
}