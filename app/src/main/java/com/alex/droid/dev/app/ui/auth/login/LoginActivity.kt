package com.alex.droid.dev.app.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alex.droid.dev.app.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.server_client_id))
            .build()

        val client = GoogleSignIn.getClient(this, gso)

        login_btn.setOnClickListener {
            startActivityForResult(client.signInIntent, RC_AUTH_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_AUTH_CODE -> {
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                if (result.isSuccess) {
                    val acct = result.signInAccount
                    val displayName = acct?.displayName
                    Timber.d("result: ${result.signInAccount?.serverAuthCode}")
                    Toast.makeText(this, "Success $displayName", Toast.LENGTH_LONG).show()
                } else {
                    Timber.e("onActivityResult ${result.status}")
                    Toast.makeText(this, "Error ${result.status}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        const val RC_AUTH_CODE = 1
    }
}