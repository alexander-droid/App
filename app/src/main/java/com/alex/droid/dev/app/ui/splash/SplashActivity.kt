package com.alex.droid.dev.app.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Wrap
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.alex.droid.dev.app.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting("Android")
        }

        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}

@Composable
fun Greeting(name: String) {
    MaterialTheme {
        Wrap(Alignment.Center) {
            Text(text = "$name!")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Greeting("Android")
}