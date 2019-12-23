package com.alex.droid.dev.app

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.base.EmptyViewModel
import com.alex.droid.dev.app.usecase.FeedUseCase
import com.alex.droid.dev.app.usecase.FeedUseCaseImpl
import com.alex.droid.dev.app.ui.post.feed.FeedViewModel
import com.alex.droid.dev.app.ui.post.feed.FeedViewModelImpl
import com.alex.droid.dev.app.ui.post.create.CreatePostViewModel
import com.alex.droid.dev.app.ui.post.detail.PostViewModel
import com.alex.droid.dev.app.ui.post.detail.PostViewModelImpl
import com.alex.droid.dev.app.usecase.CreatePostUseCase
import com.alex.droid.dev.app.usecase.GetPostUseCase
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelModule = module {
    viewModel { EmptyViewModel() }
    viewModel<FeedViewModel> { FeedViewModelImpl(get()) }
    viewModel<PostViewModel>{ PostViewModelImpl(get()) }
    viewModel { CreatePostViewModel(get()) }
}

private val appModule = module {
    factory { GsonConverterFactory.create(GsonBuilder().create()) }

    factory { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) }

    factory { CoroutineCallAdapterFactory() }

    factory {
        LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .tag("MyRequests")
            .build()
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<LoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .client(get())
            .build()
    }
}

private val useCaseModule = module {
    single<FeedUseCase> { FeedUseCaseImpl(get()) }
    single { CreatePostUseCase(get()) }
    single { GetPostUseCase(get()) }
//    single { DeletePostUseCase(get()) }
}

private val apiModule = module {
    single { get<Retrofit>().create(FeedApi::class.java) }
}

val moduleList = mutableListOf(
    appModule,
    viewModelModule,
    useCaseModule,
    apiModule
)