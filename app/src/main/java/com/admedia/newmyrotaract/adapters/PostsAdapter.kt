package com.admedia.newmyrotaract.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.databinding.PostViewBinding
import com.admedia.newmyrotaract.dataclasses.Post

class PostsAdapter(var postsList : ArrayList<Post>) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var txtPostTitle = itemView.findViewById<TextView>(R.id.txtPostTitle)
        var txtPostImage = itemView.findViewById<ImageView>(R.id.imgPostImage)
        var txtPostDate = itemView.findViewById<TextView>(R.id.txtPostDate)
        var txtPostDescription = itemView.findViewById<TextView>(R.id.txtPostDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var binding = PostViewBinding.inflate(LayoutInflater.from(parent.context))
        return PostsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.txtPostTitle.text = "${postsList[position].Title}"
        holder.txtPostImage.setImageResource(postsList[position].Image)
        holder.txtPostDescription.text = "${postsList[position].Description}"
        holder.txtPostDate.text = "${postsList[position].DateCrated}"
    }

    override fun getItemCount(): Int = postsList.size

}