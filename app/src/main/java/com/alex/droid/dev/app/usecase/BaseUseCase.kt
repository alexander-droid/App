package com.alex.droid.dev.app.usecase

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface BaseUseCase<Action, Data> {
    suspend fun async(action: Action): Deferred<Data>
}
