package com.admedia.newmyrotaract.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.admedia.newmyrotaract.constants.Constants
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.activities.MainActivity
import com.admedia.newmyrotaract.adapters.PostsAdapter
import com.admedia.newmyrotaract.databinding.PostsFragmentBinding
import com.admedia.newmyrotaract.dataclasses.Post

class PostsFragment : Fragment() {

    var postsList = ArrayList<Post>()

    lateinit var postsBinding: PostsFragmentBinding

    var postsAdapter = PostsAdapter(postsList)

    var newPost : Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addDummyPost()
        addNewPostToList()

        postsBinding = PostsFragmentBinding.inflate(layoutInflater)

        postsBinding.recyclerPosts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false)

        postsBinding.recyclerPosts.adapter = postsAdapter

        return postsBinding.root
    }

    private fun addDummyPost(){
        postsList.add(Post(R.drawable.image_eight,"Post Number 1", "This post was created on23 March This is sample text for\n" +
                "        the description for the descrition box\n" +
                "        of the post description","25 Apr", "5:32", "Professional Service Avenue"))
        postsList.add(Post(R.drawable.image_one,"Post Number 2", "This post was created on23 March This is sample text for\n" +
                "        the description for the descrition box\n" +
                "        of the post description","15 May", "5:32","Professional Service Avenue"))
        postsList.add(Post(R.drawable.image_temp_1,"Post Number 3", "This post was created on23 March This is sample text for\n" +
                "        the description for the descrition box\n" +
                "        of the post description","08 Aug", "5:32","Professional Service Avenue"))
    }

   @SuppressLint("NotifyDataSetChanged")
   private fun addNewPostToList(){

       newPost = arguments?.getBundle(Constants.NewPostKey)
       if(newPost != null){
           postsList.add(newPost?.getSerializable(Constants.NewPostKey) as Post)
           postsAdapter.notifyDataSetChanged()
       }
   }
}