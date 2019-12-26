package com.alex.droid.dev.app.usecase

interface BaseUseCase<Action, Data> {
    suspend fun run(action: Action): Data
}
