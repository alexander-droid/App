package com.alex.droid.dev.app.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.db.FeedDao
import com.alex.droid.dev.app.model.actions.ActionFeed
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.paging.NetworkState
import com.alex.droid.dev.app.paging.PagingObserver
import kotlinx.coroutines.flow.Flow

abstract class FeedUseCase : BaseUseCase<ActionFeed, Flow<List<PostEntity>>>() {

}

class FeedUseCaseImpl(
    private val feedApi: FeedApi,
    private val feedDao: FeedDao
): FeedUseCase() {

    override fun execute(action: ActionFeed): Flow<List<PostEntity>> {
        return feedDao.posts()
    }

}
//class FeedUseCaseImpl(
//    private val feedApi: FeedApi,
//    private val feedDao: FeedDao
//) : FeedUseCase() {
//
//    override fun execute(action: ActionFeed): PagingObserver<ActionFeed, Post> {
//        return PagingObserver(
//            refresh = {
//
//            },
//            pagedList = MutableLiveData<PagedList<Post>>(),
//            networkState = MutableLiveData<NetworkState>(),
//            refreshState = MutableLiveData<NetworkState>(),
//            retry = {
//
//            }
//        )
//    }
//
//}

abstract class BasePagingUseCase<Action, Data>() :
    BaseUseCase<Action, PagingObserver<Action, Data>>() {

}


abstract class BaseUseCase<Action, Data>() {

    abstract fun execute(action: Action): Data
}