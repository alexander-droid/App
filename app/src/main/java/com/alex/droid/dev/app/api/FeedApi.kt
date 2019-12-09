package com.alex.droid.dev.app.api

import com.alex.droid.dev.app.API_GET_POSTS
import com.alex.droid.dev.app.model.api.response.ResponseFeed
import io.reactivex.Single
import retrofit2.http.GET

interface FeedApi {

    @GET(API_GET_POSTS)
    fun feedPage(limit: Int?, offset: Int?): Single<ResponseFeed>
}