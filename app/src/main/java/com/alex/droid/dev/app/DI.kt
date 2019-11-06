package com.alex.droid.dev.app

import com.alex.droid.dev.app.base.EmptyViewModel
import com.alex.droid.dev.app.ui.feed.FeedViewModel
import com.alex.droid.dev.app.ui.feed.FeedViewModelImpl
import com.alex.droid.dev.app.ui.post.PostViewModel
import com.alex.droid.dev.app.ui.post.PostViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { EmptyViewModel() }
    viewModel { FeedViewModelImpl() as FeedViewModel }
    viewModel { PostViewModelImpl() as PostViewModel }
}

val moduleList = mutableListOf(
    viewModelModule
)