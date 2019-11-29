package com.alex.droid.dev.app.ui.splash

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.core.database.getStringOrNull
import androidx.lifecycle.lifecycleScope
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Wrap
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.alex.droid.dev.app.db.CommentDao
import com.alex.droid.dev.app.db.FeedDao
import com.alex.droid.dev.app.db.UserDao
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.data.user.User
import com.alex.droid.dev.app.model.entity.post.CommentEntity
import com.alex.droid.dev.app.model.entity.post.PostEntity
import com.alex.droid.dev.app.model.entity.user.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    val feedDao by inject<FeedDao>()

    val userDao by inject<UserDao>()

    val commentDao by inject<CommentDao>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Greeting("Android")
            }
        }

        lifecycleScope.launch {
            Timber.d("launch")
            withContext(Dispatchers.IO) {
                Timber.d("withContext")
                feedDao.insert(
                    listOf(
                        PostEntity(
                            postId = "post_id1",
                            postMessage = "Post text 1",
                            postVideo = null,
                            postImage = null,
                            postUserId = "user_id1"
                        ),
                        PostEntity(
                            postId = "post_id2",
                            postMessage = "Post text 2",
                            postVideo = null,
                            postImage = null,
                            postUserId = "user_id2"
                        )
                    )
                )

                userDao.insert(
                    listOf(
                        UserEntity(
                            userId = "user_id1",
                            userName = "John",
                            userAvatar = null
                        ),
                        UserEntity(
                            userId = "user_id2",
                            userName = "Bob",
                            userAvatar = null
                        )
                    )
                )

                commentDao.insert(
                    listOf(
                        CommentEntity(
                            commentId = "comment1",
                            commentText = "Comment text 1",
                            commentPostId = "post_id1"
                        ),
                        CommentEntity(
                            commentId = "comment2",
                            commentText = "Comment text 2",
                            commentPostId = "post_id1"
                        ),
                        CommentEntity(
                            commentId = "comment3",
                            commentText = "Comment text 3",
                            commentPostId = "post_id2"
                        ),
                        CommentEntity(
                            commentId = "comment4",
                            commentText = "Comment text 4",
                            commentPostId = "post_id2"
                        )
                    )
                )

                feedDao.postsCursor().use {
                    Timber.d("Cursor: ${DatabaseUtils.dumpCursorToString(it)}")

                    val list = mutableListOf<Post>()
                    while (it.moveToNext()) {
                        list.add(
                            Post(
                                id = it.getString(it.getColumnIndex("postId")),
                                text = it.getString(it.getColumnIndex("postMessage")),
                                video = it.getString(it.getColumnIndex("postVideo")),
                                image = it.getString(it.getColumnIndex("postImage")),
                                date = it.getString(it.getColumnIndex("postDate")),
                                user = User(
                                    id = it.getString(it.getColumnIndex("userId")),
                                    name = it.getString(it.getColumnIndex("userName")),
                                    avatar = it.getString(it.getColumnIndex("userAvatar"))
                                ),
                                isLiked = it.getInt(it.getColumnIndex("postIsLiked")) != 0
                            )
                        )
                    }

                    list.forEach {
                        Timber.d("Success: $it")
                    }
                }

//                feedDao.postsAndUsers().forEach {
//                    Timber.d("PostsAndUsers: ${it}")
//                }
            }

            /*val flo = flow<Int> {
                Timber.d("flow ${Thread.currentThread().name}")
                emit(55)
            }

            flo.collect {
                Timber.d("flow collect $it, ${Thread.currentThread().name}")
            }

            Timber.d("_collect, ${Thread.currentThread().name}")
            feedDao.posts().collect {
                Timber.d("collect ${Thread.currentThread().name}, $it")
            }
            Timber.d("_collect success, ${Thread.currentThread().name}")*/


//            Timber.d("_observe 1")
//            feedDao.posts().asLiveData().observe(this@SplashActivity, Observer {
//                Timber.d("observe 1 $it")
//            })
//
//            Timber.d("_observe 2")
//            feedDao.posts().asLiveData().observe(this@SplashActivity, Observer {
//                Timber.d("observe 2 $it")
//            })

//            Timber.d("_collect 1")
//            feedDao.posts().collect {
//                Timber.d("collect 1 $it")
//            }
//            Timber.d("_collect success")

//            Timber.d("_observe 3")
//            feedDao.posts().asLiveData().observe(this@SplashActivity, Observer {
//                Timber.d("observe 3 $it")
//            })
//
//            Timber.d("_collect 2")
//            feedDao.posts().collect {
//                Timber.d("collect 2 $it")
//            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Wrap(Alignment.Center) {
            Greeting("Android")
        }
    }
}