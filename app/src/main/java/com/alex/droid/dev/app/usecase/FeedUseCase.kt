package com.alex.droid.dev.app.usecase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import androidx.paging.toLiveData
import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.api.response.ResponseFeed
import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.data.post.Privacy
import com.alex.droid.dev.app.model.data.user.User
import com.alex.droid.dev.app.paging.LoadingState
import com.alex.droid.dev.app.paging.PagingData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

interface FeedUseCase {
    fun feed(): PagingData<Post>
}

class FeedUseCaseImpl(private val feedApi: FeedApi) : FeedUseCase {

    override fun feed(): PagingData<Post> {
        val sourceFactory = DataSourceFactory(feedApi)

        val livePagedList = sourceFactory.toLiveData(
            config = PagedList.Config.Builder()
                .setPageSize(1)
                .setEnablePlaceholders(false)
                .build()
        )

        val refreshState = sourceFactory.sourceLiveData.switchMap {
            it.initialLoad
        }

        val loadingState = sourceFactory.sourceLiveData.switchMap {
            it.loadingState
        }

        return PagingData(
            pagedList = livePagedList,
            loadingState = loadingState,
            refreshState = refreshState,
            retry = {
                sourceFactory.sourceLiveData.value?.retryAll()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }
}

class DataSourceFactory(private val feedApi: FeedApi) : DataSource.Factory<Int, Post>() {

    val sourceLiveData = MutableLiveData<FeedDataSource>()

    override fun create(): DataSource<Int, Post> {
        val source = FeedDataSource(feedApi)
        sourceLiveData.postValue(source)
        return source
    }
}

class FeedDataSource(
    private val feedApi: FeedApi
) : PositionalDataSource<Post>() {

    val loadingState = MutableLiveData<LoadingState>()

    val initialLoad = MutableLiveData<LoadingState>()

    private var disposable: Disposable? = null

    private var retry: (() -> Any)? = null

    fun retryAll() {
        retry?.invoke()
        retry = null
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Post>) {
        Timber.d("loadInitial")
        loadingState.postValue(LoadingState.LOADING)
        initialLoad.postValue(LoadingState.LOADING)

        disposable?.dispose()
//        disposable = feedApi.feedPage(limit = params.pageSize, offset = 0)
        disposable = Observable.just(getFakeResponse()).delay(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                retry = null
                loadingState.postValue(LoadingState.LOADED)
                initialLoad.postValue(LoadingState.LOADED)
                callback.onResult(it.list, 0)
            }, {
                Timber.e(it)
                retry = { loadInitial(params, callback) }
                loadingState.value = LoadingState.error(it.message)
                initialLoad.value = LoadingState.error(it.message)
            })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Post>) {
        Timber.d("loadRange")
        loadingState.postValue(LoadingState.LOADING)

        disposable?.dispose()
//        disposable = feedApi.feedPage(limit = params.loadSize, offset = params.startPosition)
        disposable = Observable.just(getFakeResponse()).delay(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                retry = null
                loadingState.postValue(LoadingState.LOADED)
                callback.onResult(it.list)
            }, {
                Timber.e(it)
                retry = { loadRange(params, callback) }
                loadingState.value = LoadingState.error(it.message)
            })
    }


    private fun getFakeResponse(): ResponseFeed {
        return ResponseFeed(
            list = mutableListOf<Post>().apply {
                for (i in 0 until 30) {
                    add(Post(
                        id = Random(1000000).nextLong(),
                        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        address = Address(
                            lat = 45.43432,
                            lng = 34.54353,
                            name = "Some address name",
                            address = "Some address, 55"
                        ),
                        video = "",
                        image = "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg",
                        user = User(
                            id = Random(1000000).nextLong(),
                            name = "User Name",
                            avatar = "https://i.pinimg.com/236x/0a/dd/87/0add874e1ea0676c4365b2dd7ddd32e3--james-cameron-james-darcy.jpg"
                        ),
                        privacy = Privacy.PRIVATE
                    ))
                }
            }
        )
    }
}