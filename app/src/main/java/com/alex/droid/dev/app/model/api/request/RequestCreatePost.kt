package com.alex.droid.dev.app.model.api.request

import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.data.post.Privacy

data class RequestCreatePost(
    val message: String?,
    val address: Address?,
    val privacy: Privacy?
)