package com.alex.droid.dev.app

import com.alex.droid.dev.app.base.EmptyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { EmptyViewModel() }
}

val moduleList = mutableListOf(
    viewModelModule
)