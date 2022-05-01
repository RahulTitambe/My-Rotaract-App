package com.admedia.newmyrotaract.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.adapters.PostsAdapter
import com.admedia.newmyrotaract.databinding.CreateNewPostBinding
import com.admedia.newmyrotaract.databinding.PostViewBinding
import com.admedia.newmyrotaract.databinding.PostsFragmentBinding
import com.admedia.newmyrotaract.dataclasses.Post

class PostsFragment : Fragment() {

    var postsList = ArrayList<Post>()
    var postsAdapter = PostsAdapter(postsList)
    lateinit var postsBinding: PostsFragmentBinding
    var createNewPostFragment = CreateNewPostFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addDummyPost()

        postsBinding = PostsFragmentBinding.inflate(layoutInflater)

        postsBinding.recyclerPosts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false)

        postsBinding.recyclerPosts.adapter = postsAdapter
        createNewPostFragment.onSaveBtnClickListener = MyOnPostListener()


        return  postsBinding.root
    }

    fun addDummyPost(){
        postsList.add(Post(R.drawable.image_eight,"Post Number 1", "This post was created on23 March","25 Apr", "5:32"))
        postsList.add(Post(R.drawable.image_one,"Post Number 2", "This post was created on23 March","15 May", "5:32"))
        postsList.add(Post(R.drawable.image_eight,"Post Number 3", "This post was created on23 March","08 Aug", "5:32"))
    }

    inner class MyOnPostListener : CreateNewPostFragment.OnSaveBtnClickListener{
        @SuppressLint("NotifyDataSetChanged")
        override fun onSave(post: Post) {
                postsList.add(post)
                postsAdapter.notifyDataSetChanged()
        }
    }
}