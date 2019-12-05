package com.alex.droid.dev.app

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.api.mock.FakeFeedApi
import com.alex.droid.dev.app.base.EmptyViewModel
import com.alex.droid.dev.app.db.CommentDao
import com.alex.droid.dev.app.db.DataBase
import com.alex.droid.dev.app.db.FeedDao
import com.alex.droid.dev.app.db.UserDao
import com.alex.droid.dev.app.model.entity.post.CommentEntity
import com.alex.droid.dev.app.repository.FeedUseCase
import com.alex.droid.dev.app.repository.FeedUseCaseImpl
import com.alex.droid.dev.app.ui.feed.FeedViewModel
import com.alex.droid.dev.app.ui.feed.FeedViewModelImpl
import com.alex.droid.dev.app.ui.post.PostViewModel
import com.alex.droid.dev.app.ui.post.PostViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors

private val viewModelModule = module {
    viewModel { EmptyViewModel() }
    viewModel { FeedViewModelImpl(get()) as FeedViewModel }
    viewModel { PostViewModelImpl() as PostViewModel }
}

private val appModule = module {
    single {
        Room.databaseBuilder(get(), DataBase::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    Timber.tag("FeedViewModel").w("Room onCreate")
                    GlobalScope.launch {
                        val userList = FakeFeedApi.users
                        val postList = FakeFeedApi.posts
                        val commentList = mutableListOf<CommentEntity>()
                        postList.forEach { post ->
                            commentList.addAll(FakeFeedApi.getComments(userId = userList[Random().nextInt(userList.size)].id, postId = post.id))
                        }

                        get<UserDao>().insert(userList)
                        Timber.tag("FeedViewModel").v("UserDao insert")
                        get<FeedDao>().insert(postList)
                        Timber.tag("FeedViewModel").v("FeedDao insert")
                        get<CommentDao>().insert(commentList)
                        Timber.tag("FeedViewModel").v("CommentDao insert")
                    }
                }
            })
            .setQueryExecutor(Dispatchers.IO.asExecutor())
            .setTransactionExecutor(Dispatchers.IO.asExecutor())
            .build()
    }
}

private val dbModule = module {
    single { get<DataBase>().feedDao() }
    single { get<DataBase>().userDao() }
    single { get<DataBase>().commentDao() }
}

private val useCaseModule = module {
    single { FeedUseCaseImpl(get(), get()) as FeedUseCase }
}

private val apiModule = module {
    single { FakeFeedApi() as FeedApi }
}

val moduleList = mutableListOf(
    appModule,
    dbModule,
    viewModelModule,
    useCaseModule,
    apiModule
)