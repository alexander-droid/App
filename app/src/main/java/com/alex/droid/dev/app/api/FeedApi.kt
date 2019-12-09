package com.alex.droid.dev.app.api

import com.alex.droid.dev.app.API_GET_POSTS
import com.alex.droid.dev.app.model.api.response.ResponseFeed
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {

    @GET(API_GET_POSTS)
    fun feedPage(@Query("limit") limit: Int?, @Query("offset") offset: Int?): Single<ResponseFeed>
}