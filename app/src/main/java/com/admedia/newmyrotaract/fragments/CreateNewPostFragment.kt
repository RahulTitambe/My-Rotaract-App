package com.admedia.newmyrotaract.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.databinding.CreateNewPostBinding
import com.admedia.newmyrotaract.dataclasses.Post

class CreateNewPostFragment : Fragment() {

    lateinit var bindingCreateNewPostBinding: CreateNewPostBinding
    var imageId : Int = R.drawable.ic_image_view
    interface OnSaveBtnClickListener {
        fun onSave (post : Post)
    }

    var onSaveBtnClickListener : OnSaveBtnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingCreateNewPostBinding = CreateNewPostBinding.inflate(layoutInflater)
        SaveBtnListener()

        return bindingCreateNewPostBinding.root
    }

    fun SaveBtnListener(){
        bindingCreateNewPostBinding.btnSavePost.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this)
                .commit()
            imageId = bindingCreateNewPostBinding.imgPostImagePicker.id
            onSaveBtnClickListener?.onSave(Post(imageId,
            bindingCreateNewPostBinding.edtTitle.text.toString(),
            bindingCreateNewPostBinding.edtDescription.text.toString(),
            "25 Mar", "20:55 PM"))
        }
    }

}