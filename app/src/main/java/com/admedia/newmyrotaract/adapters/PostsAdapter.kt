package com.admedia.newmyrotaract.adapters

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.databinding.PostViewBinding
import com.admedia.newmyrotaract.dataclasses.Post

class PostsAdapter(var postsList: ArrayList<Post>) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    lateinit var binding : PostViewBinding

    lateinit var context : Context

    private var imageWidth = 0
    private var imageHeight = 0

    private var setHeight = 0
    private var setWidth = 0

    private var imageViewWidth = 0

    lateinit var displayMetrics : DisplayMetrics

    private var ratio_w = 0.0
    private var ratio_h = 0.0

    inner class PostsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {

        binding = PostViewBinding.inflate(LayoutInflater.from(parent.context))

        context = parent.context

        displayMetrics = parent.resources.displayMetrics

        imageViewWidth = displayMetrics.widthPixels

        return PostsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

        binding.txtPostTitle.text = postsList[position].Title
        binding.imgPostImage.setImageResource(postsList[position].Image)
        binding.txtPostDescription.text = postsList[position].Description
        binding.txtPostDate.text = postsList[position].DateCrated
        binding.txtPostBy.text = postsList[position].PostBy

        imageHeight = binding.imgPostImage.drawable.intrinsicHeight
        imageWidth = binding.imgPostImage.drawable.intrinsicWidth

        SetImageViewHeightAndWidth()

    }

    override fun getItemCount(): Int = postsList.size

    private fun SetImageViewHeightAndWidth() {

        ratio_w = (imageWidth/imageViewWidth).toDouble()
        ratio_h = (imageHeight/1720).toDouble()

        if(ratio_w > ratio_h){
            setHeight = (imageHeight/ratio_w).toInt()
        }
        else{
            setHeight = (imageHeight/ratio_h).toInt()
        }

        if(setHeight <= 1720){
            binding.imgPostImage.layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        }
        else{
            binding.imgPostImage.layoutParams.height = 1720
        }
    }
}