package com.admedia.newmyrotaract.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.activities.MainActivity
import com.admedia.newmyrotaract.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var loginBinding : LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        loginBinding = LoginFragmentBinding.inflate(layoutInflater)

        listeners()

        return loginBinding.root
    }

    private fun listeners(){

        loginBinding.btnLoginLogin.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        loginBinding.txtLoginForgetPassword.setOnClickListener {

            val forgetPasswordFragment = ForgetPasswordFragment()

            activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.enter_from_right,
                    R.anim.exit_to_right,
                    R.anim.enter_from_right,
                    R.anim.exit_to_right)
                ?.replace(R.id.containerLoginActivity, forgetPasswordFragment, null)
                ?.commit()

        }

    }

}