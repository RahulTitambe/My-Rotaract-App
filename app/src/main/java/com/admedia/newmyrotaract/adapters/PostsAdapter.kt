package com.admedia.newmyrotaract.adapters


import android.content.Context
import android.util.DisplayMetrics
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.databinding.PostViewBinding
import com.admedia.newmyrotaract.dataclasses.Post

class PostsAdapter(private var postsList: ArrayList<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private lateinit var binding: PostViewBinding

    private lateinit var context: Context

    private var imageWidth = 0
    private var imageHeight = 0

    private var setHeight = 0

    private var imageViewWidth = 0

    private lateinit var displayMetrics: DisplayMetrics

    private var ratioW = 0.0
    private var ratioH = 0.0

    interface SharePost {
        fun onSharePostListener(post: Post, postImage: ImageView, binding: PostViewBinding)
    }

    interface LikePost {
        fun onLikeClickListener(post: Post, btnLike: ImageView, txtLikes: TextView)
    }

    interface OpenDetailedPost {
        fun onDetailedPostClickListener(post: Post, position: Int)
    }

    var sharePost: SharePost? = null
    var likePost: LikePost? = null
    var detailedPost: OpenDetailedPost? = null

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var btnLike = itemView.findViewById<ImageView>(R.id.imgLikeButton)
        private var btnShare = itemView.findViewById<ImageView>(R.id.imgShareButton)
        private var txtLikes = itemView.findViewById<TextView>(R.id.txtLikes)
        private var imgPostImage = itemView.findViewById<ImageView>(R.id.imgPostImage)
        private var txtPostDescription = itemView.findViewById<TextView>(R.id.txtPostDescription)

        init {
            btnLike.setOnClickListener {
                likePost?.onLikeClickListener(postsList[adapterPosition], btnLike, txtLikes)
            }

            btnShare.setOnClickListener {
                sharePost?.onSharePostListener(postsList[adapterPosition], imgPostImage, binding)
            }

            imgPostImage.setOnClickListener {
                detailedPost?.onDetailedPostClickListener(
                    postsList[adapterPosition],
                    adapterPosition
                )
            }

            txtPostDescription.setOnClickListener {
                detailedPost?.onDetailedPostClickListener(
                    postsList[adapterPosition],
                    adapterPosition
                )
            }
        }
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
        binding.txtLikes.text = postsList[position].Likes.toString()

        imageHeight = binding.imgPostImage.drawable.intrinsicHeight
        imageWidth = binding.imgPostImage.drawable.intrinsicWidth

        setImageViewHeightAndWidth()

    }

    override fun getItemCount(): Int = postsList.size

    private fun setImageViewHeightAndWidth() {

        ratioW = (imageWidth / imageViewWidth).toDouble()
        ratioH = (imageHeight / 1720).toDouble()

        setHeight = if (ratioW > ratioH) {
            (imageHeight / ratioW).toInt()
        } else {
            (imageHeight / ratioH).toInt()
        }

        if (setHeight <= 1720) {
            binding.imgPostImage.layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        } else {
            binding.imgPostImage.layoutParams.height = 1720
        }
    }

}