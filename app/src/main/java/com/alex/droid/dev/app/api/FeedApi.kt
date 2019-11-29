package com.alex.droid.dev.app.api

import com.alex.droid.dev.app.model.data.post.Post
import io.reactivex.Single

interface FeedApi {

    fun feedPage(filter: String?, lastId: String?): Single<List<Post>>
}