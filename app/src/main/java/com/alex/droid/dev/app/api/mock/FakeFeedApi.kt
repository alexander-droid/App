package com.alex.droid.dev.app.api.mock

import com.alex.droid.dev.app.api.FeedApi
import com.alex.droid.dev.app.model.data.post.Comment
import com.alex.droid.dev.app.model.data.post.Post
import com.alex.droid.dev.app.model.data.user.User
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class FakeFeedApi : FeedApi {

    companion object {
        fun generatePosts(count: Int = 100): MutableList<Post> {
            return mutableListOf<Post>().apply {
                for (i in 0 until count) {
                    add(Post(
                        id = UUID.randomUUID().toString(),
                        text = "$i. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        video = getRandomVideoUrl(),
                        image = getRandomImageUrl(),
                        isLiked = false,
                        date = "2019-10-28 14:15:45.709",
                        user = User(
                            id = UUID.randomUUID().toString(),
                            name = "$i Name",
                            avatar = getRandomAvatarUrl()
                        )
                    ))
                }
            }
        }

        private fun getComments(): MutableList<Comment> {
            return MutableList(20) {
                Comment(
                    id = UUID.randomUUID().toString(),
                    message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                    user = User(
                        id = UUID.randomUUID().toString(),
                        name = "$it Name",
                        avatar = getRandomAvatarUrl()
                    )
                )
            }
        }


        private fun getRandomVideoUrl(): String {
            return videoUrlList[Random().nextInt(videoUrlList.size)]
        }

        private fun getRandomImageUrl(): String {
            return imageUrlList[Random().nextInt(imageUrlList.size)]
        }

        private fun getRandomAvatarUrl(): String {
            return avatarUrlList[Random().nextInt(avatarUrlList.size)]
        }


        private val avatarUrlList = mutableListOf(
            "https://peopletalk.ru/wp-content/uploads/2016/11/1480331127.jpg",
            "https://img.cinemablend.com/filter:scale/quill/d/e/6/c/9/6/de6c96f1e9871aef148dbc51fb9a5bc90ff25314.jpg"
        )

        private val videoUrlList = mutableListOf(
            "https://ak6.picdn.net/shutterstock/videos/1014547376/preview/stock-footage-slow-motion-young-woman-running-in-purple-flowers-field-in-iceland-travel-and-adventure-concept.webm",
            "https://ak9.picdn.net/shutterstock/videos/1018564099/preview/stock-footage-two-friends-on-summer-vacation-or-holiday-run-on-wooden-boardwalk-on-alpine-mountain-lake-jump.webm",
            "https://ak9.picdn.net/shutterstock/videos/14716189/preview/stock-footage-freshwater-river-fishing-male-and-female-casting-at-sunrise.webm",
            "https://ak9.picdn.net/shutterstock/videos/1018746619/preview/stock-footage-close-up-macro-blue-eye-opening-human-iris-natural-beauty.webm",
            "https://ak8.picdn.net/shutterstock/videos/1025545688/preview/stock-footage-green-jungle-trees-and-palms-against-blue-sky-and-shining-sun-travel-vacation-nature-concept-look.webm",
            "https://ak8.picdn.net/shutterstock/videos/1023566578/preview/stock-footage-beautiful-sunrise-world-skyline-planet-earth-from-space-planet-earth-rotating-animation-clip.webm",
            "https://movietrailers.apple.com/movies/independent/memory-the-origins-of-alien/memory-the-origins-of-alien-trailer-1_h1080p.mov"
        )

        private val imageUrlList = mutableListOf(
            "https://wallpaperaccess.com/full/81234.jpg",
            "http://hdwpro.com/wp-content/uploads/2017/01/Stunning-Full-HD-Wallpaper.jpg",
            "http://www.fullhdwpp.com/wp-content/uploads/Wet-Urban-New-York-City-37_www.FullHDWpp.com_.jpg",
            "https://hdwallpaper1080.com/wp-content/uploads/27945941.jpg",
            "https://wallpapercave.com/wp/JAA5qgn.jpg",
            "https://wallpapercave.com/wp/wp367487.jpg",
            "https://images.wallpaperscraft.com/image/minnewanka_lake_canada_mountains_118743_1920x1080.jpg"
        )
    }

    private val feed by lazy {
        generatePosts()
    }

    override fun feedPage(filter: String?, lastId: String?): Single<List<Post>> {
        return Single.fromCallable<List<Post>> {
            val page = mutableListOf<Post>()

            var lastIdFound = lastId == null
            var itemsQueried = 0
            run {
                feed.forEach {
                    if (lastIdFound) {
                        page.add(it)
                        itemsQueried += 1
                    } else if (it.id == lastId) {
                        lastIdFound = true
                    }

                    if (itemsQueried == 20) {
                        return@run
                    }
                }
            }
            return@fromCallable page
        }.subscribeOn(Schedulers.io())
    }
}