package com.admedia.newmyrotaract.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.adapters.PostsAdapter
import com.admedia.newmyrotaract.constants.Constants
import com.admedia.newmyrotaract.databinding.PostViewBinding
import com.admedia.newmyrotaract.databinding.PostsFragmentBinding
import com.admedia.newmyrotaract.dataclasses.Post

class PostsFragment : Fragment() {

    private var postsList = ArrayList<Post>()

    private lateinit var postsBinding: PostsFragmentBinding
    private lateinit var postView: View

    private lateinit var btnLike: ImageView

    private var postsAdapter = PostsAdapter(postsList)

    private var newPost: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        addDummyPost()
        addNewPostToList()

        postsBinding = PostsFragmentBinding.inflate(layoutInflater)
        postView = View.inflate(context, R.layout.post_view, null)

        btnLike = postView.findViewById(R.id.imgLikeButton)

        postsBinding.recyclerPosts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        postsBinding.recyclerPosts.adapter = postsAdapter

        postsAdapter.sharePost = MyOnPostClickListener()
        postsAdapter.likePost = MyLikeClickListener()
        postsAdapter.detailedPost = MyDetailedPostClickListener()

        return postsBinding.root
    }

    private fun addDummyPost() {
        postsList.add(
            Post(
                R.drawable.image_eight,
                "Post Number 1",
                "This post 1 was created on23 March This is sample text for\n" +
                        "        the description for the description box\n" +
                        "        of the post description",
                "25 Apr",
                "5:32",
                "Professional Service Avenue",
                5,
                false
            )
        )
        postsList.add(
            Post(
                R.drawable.image_one,
                "Post Number 2",
                "This post 2 was created on23 March This is sample text for\n" +
                        "        the description for the description box\n" +
                        "        of the post description",
                "15 May",
                "5:32",
                "Professional Service Avenue",
                0,
                false
            )
        )
        postsList.add(
            Post(
                R.drawable.image_temp_1,
                "Post Number 3",
                "This post 3 was created on23 March This is sample text for\n" +
                        "        the description for the description box\n" +
                        "        of the post description",
                "08 Aug",
                "5:32",
                "Professional Service Avenue",
                6,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addNewPostToList() {

        newPost = arguments?.getBundle(Constants.NewPostKey)
        if (newPost != null) {
            postsList.add(newPost?.getSerializable(Constants.NewPostKey) as Post)
            postsAdapter.notifyDataSetChanged()
        }
    }

    inner class MyOnPostClickListener : PostsAdapter.SharePost {
        override fun onSharePostListener(
            post: Post,
            btnShare: ImageView,
            binding: PostViewBinding
        ) {

            val drawable = binding.imgPostImage.drawable as BitmapDrawable
            val bitmap: Bitmap = drawable.bitmap

            val bitmapPath: String =
                MediaStore.Images.Media.insertImage(context?.contentResolver, bitmap, "title", null)

            val uri: Uri = Uri.parse(bitmapPath)

            val shareIntent = Intent()
            shareIntent.action = (Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Project Name: ${post.Title}\n\n ${post.Description}"
            )
            shareIntent.type = "image/png"

            startActivity(Intent.createChooser(shareIntent, "Share with"))
        }
    }

    inner class MyLikeClickListener : PostsAdapter.LikePost {
        override fun onLikeClickListener(post: Post, btnLike: ImageView, txtLikes: TextView) {

            if (!post.LikeStatus) {
                post.Likes = post.Likes + 1
                txtLikes.text = post.Likes.toString()
                btnLike.setImageResource(R.drawable.ic_pink_heart)
                post.LikeStatus = true
            } else {
                post.Likes = post.Likes - 1
                txtLikes.text = post.Likes.toString()
                btnLike.setImageResource(R.drawable.ic_heart_outline)
                post.LikeStatus = false
            }
        }
    }

    inner class MyDetailedPostClickListener : PostsAdapter.OpenDetailedPost {
        override fun onDetailedPostClickListener(
            post: Post,
            position: Int,
        ) {

            val bundle = Bundle()
            val detailedFragment = DetailedPostFragment()

//            binding.txtPostDescription.maxLines = (Integer.MAX_VALUE)
//            binding.txtPostDescription.ellipsize = null

            bundle.putString("title", post.Title)
            bundle.putString("desc", post.Description)
            bundle.putInt("image", post.Image)

            detailedFragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_right,
                    R.anim.enter_from_right,
                    R.anim.exit_to_right
                )
                ?.replace(R.id.mainContainer, detailedFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}