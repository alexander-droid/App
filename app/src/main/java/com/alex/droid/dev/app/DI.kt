package com.alex.droid.dev.app

import androidx.room.Room
import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.api.mock.FakeFeedApi
import com.alex.droid.dev.app.base.EmptyViewModel
import com.alex.droid.dev.app.db.DataBase
import com.alex.droid.dev.app.repository.FeedUseCase
import com.alex.droid.dev.app.repository.FeedUseCaseImpl
import com.alex.droid.dev.app.ui.feed.FeedViewModel
import com.alex.droid.dev.app.ui.feed.FeedViewModelImpl
import com.alex.droid.dev.app.ui.post.PostViewModel
import com.alex.droid.dev.app.ui.post.PostViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
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
            .setQueryExecutor(Executors.newSingleThreadExecutor())
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