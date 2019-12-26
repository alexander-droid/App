package com.alex.droid.dev.app.usecase

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.api.request.RequestGetPost
import com.alex.droid.dev.app.model.api.response.ResponsePost

class GetPostUseCase(private val feedApi: FeedApi) : BaseUseCase<RequestGetPost, ResponsePost> {

    override suspend fun run(action: RequestGetPost): ResponsePost {
        return feedApi.getPost(action.id)
    }

}