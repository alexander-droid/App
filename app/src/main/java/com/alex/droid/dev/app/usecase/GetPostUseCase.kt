package com.alex.droid.dev.app.usecase

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.api.request.RequestGetPost
import com.alex.droid.dev.app.model.api.response.ResponsePost
import kotlinx.coroutines.Deferred

class GetPostUseCase(private val feedApi: FeedApi) : BaseUseCase<RequestGetPost, ResponsePost> {

    override suspend fun async(action: RequestGetPost): Deferred<ResponsePost> {
        return feedApi.getPostAsync(action.id)
    }

}