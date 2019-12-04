package com.alex.droid.dev.app.repository

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.db.FeedDao
import com.alex.droid.dev.app.model.actions.ActionFeed
import com.alex.droid.dev.app.model.entity.PostData
import com.alex.droid.dev.app.paging.PagingObserver
import kotlinx.coroutines.flow.Flow

abstract class FeedUseCase : BaseUseCase<ActionFeed, Flow<List<PostData>>>() {

}

class FeedUseCaseImpl(
    private val feedApi: FeedApi,
    private val feedDao: FeedDao
): FeedUseCase() {

    override fun execute(action: ActionFeed): Flow<List<PostData>> {
        return feedDao.postsAndUsers()
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