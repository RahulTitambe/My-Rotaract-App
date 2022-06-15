package com.admedia.newmyrotaract.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {

    private var LoginFragment = LoginFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right)
            .add(R.id.containerLoginActivity, LoginFragment, null)
            .commit()

    }
}