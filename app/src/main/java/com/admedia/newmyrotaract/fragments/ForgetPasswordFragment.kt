package com.admedia.newmyrotaract.fragments

import android.content.Context
import android.content.Context.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.databinding.ForgetPasswordBinding
import kotlin.math.log

class ForgetPasswordFragment : Fragment() {

    lateinit var forgetPasswordBinding : ForgetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        forgetPasswordBinding = ForgetPasswordBinding.inflate(layoutInflater)

        forgetPasswordBinding.containerEnterOTP.setVisibility(View.GONE)

        forgetPasswordBinding.btnFrgtPassValidateOTP.isClickable = false

        listeners()
        onTextChangeListeners()

        return forgetPasswordBinding.root

    }

    private fun listeners(){

        forgetPasswordBinding.txtLoginBtn.setOnClickListener {

            val loginFragment = LoginFragment()

            activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_right,
                    R.anim.enter_from_right,
                    R.anim.exit_to_right)
                ?.replace(R.id.containerLoginActivity, loginFragment, null)
                ?.commit()
        }


        //checking for valid email and checking in the database for the email address
        // and sending OTP to entered email address
        forgetPasswordBinding.btnFrgtPassGenerateOTP.setOnClickListener {

            if (forgetPasswordBinding.edtFrgtPassEmail.text.length >= 5) {

                if(forgetPasswordBinding.edtFrgtPassEmail.text.toString().contains("@")) {

                    forgetPasswordBinding.containerGenerateOTP.setVisibility(View.GONE)

                    forgetPasswordBinding.txtEnteredEmail.text =
                        forgetPasswordBinding.edtFrgtPassEmail.text.toString()

                    forgetPasswordBinding.containerEnterOTP.setVisibility(View.VISIBLE)

                    hideKeyboard()
                }
                else {
                    Toast.makeText(context, "Enter a valid Email ID", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(context, "Enter a valid Email ID", Toast.LENGTH_LONG).show()
            }
        }

        //hiding the current container and enabling the container of entering email
        forgetPasswordBinding.txtEditEmail.setOnClickListener {

            forgetPasswordBinding.containerEnterOTP.setVisibility(View.GONE)

            forgetPasswordBinding.containerGenerateOTP.setVisibility(View.VISIBLE)
        }
    }

    private fun onTextChangeListeners(){

    }

    private fun hideKeyboard(){

    }
}