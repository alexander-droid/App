package com.alex.droid.dev.app.usecase

abstract class BaseUseCase<Action, Data>() {

    abstract fun execute(action: Action): Data
}
