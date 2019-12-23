package com.alex.droid.dev.app.usecase

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.api.request.RequestDeletePost
import kotlinx.coroutines.Deferred

//class DeletePostUseCase(private val feedApi: FeedApi) : BaseUseCase<RequestDeletePost, Unit> {
//
//    override suspend fun async(action: RequestDeletePost): Deferred<Unit> {
//        return feedApi.deletePostAsync(action.id)
//    }
//}