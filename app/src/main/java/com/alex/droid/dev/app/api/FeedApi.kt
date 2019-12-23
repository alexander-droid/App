package com.alex.droid.dev.app.api

import com.alex.droid.dev.app.API_CREATE_POST
import com.alex.droid.dev.app.API_DELETE_POST
import com.alex.droid.dev.app.API_GET_POST
import com.alex.droid.dev.app.API_GET_POSTS
import com.alex.droid.dev.app.model.api.request.RequestCreatePost
import com.alex.droid.dev.app.model.api.response.ResponseFeed
import com.alex.droid.dev.app.model.api.response.ResponsePost
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface FeedApi {

    @GET(API_GET_POSTS)
    fun feedPage(@Query("limit") limit: Int?, @Query("offset") offset: Int?): Single<ResponseFeed>

    @POST(API_CREATE_POST)
    suspend fun createPostAsync(@Body body: RequestCreatePost): Deferred<Unit>

    @DELETE(API_DELETE_POST)
    suspend fun deletePostAsync(@Path("id") id: Long): Deferred<Unit>

    @GET(API_GET_POST)
    suspend fun getPostAsync(id: Long): Deferred<ResponsePost>
}