package com.alex.droid.dev.app.api

import com.alex.droid.dev.app.*
import com.alex.droid.dev.app.model.api.request.RequestCreatePost
import com.alex.droid.dev.app.model.api.response.ResponseFeed
import com.alex.droid.dev.app.model.api.response.ResponsePost
import io.reactivex.Single
import retrofit2.http.*

interface FeedApi {

    @GET(API_GET_POSTS)
    fun feedPage(@Query("limit") limit: Int?, @Query("offset") offset: Int?): Single<ResponseFeed>

    @POST(API_CREATE_POST)
    suspend fun createPost(@Body body: RequestCreatePost)

    @DELETE(API_DELETE_POST)
    suspend fun deletePost(@Path("id") id: Long)

    @GET(API_GET_POST)
    suspend fun getPost(id: Long): ResponsePost

    @GET(API_TEST)
    suspend fun test()
}