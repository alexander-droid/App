package com.alex.droid.dev.app.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.lifecycleScope
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Wrap
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.alex.droid.dev.app.MainActivity
import com.alex.droid.dev.app.api.mock.FakeFeedApi
import com.alex.droid.dev.app.db.CommentDao
import com.alex.droid.dev.app.db.FeedDao
import com.alex.droid.dev.app.db.UserDao
import com.alex.droid.dev.app.model.entity.post.CommentEntity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

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
            val userList = FakeFeedApi.users
            val postList = FakeFeedApi.posts

            val commentList = mutableListOf<CommentEntity>()

            postList.forEach { post ->
                commentList.addAll(FakeFeedApi.getComments(userId = userList[Random().nextInt(userList.size)].id, postId = post.id))
            }

            userDao.insert(userList)
            feedDao.insert(postList)
            commentDao.insert(commentList)

            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
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