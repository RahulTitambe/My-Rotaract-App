package com.admedia.newmyrotaract.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ablanco.zoomy.Zoomy
import com.admedia.newmyrotaract.databinding.DetailedPostBinding

class DetailedPostFragment : Fragment(){

    private lateinit var builder : Zoomy.Builder

    private lateinit var binding : DetailedPostBinding

    private var imageWidth = 0.0
    private var imageHeight = 0.0

    private var setHeight = 0.0

    private var imageViewWidth = 0.0

    private lateinit var displayMetrics: DisplayMetrics

    private var ratioW = 0.0
    private var ratioH = 0.0

    private var title : String? = null
    private var description : String? = null
    private var image : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DetailedPostBinding.inflate(layoutInflater)

        builder = Zoomy.Builder(activity)
            .target(binding.imgDetailedPostImage)
            .animateZooming(false)
            .enableImmersiveMode(false)

        builder.register()

        title = arguments?.getString("title")
        description = arguments?.getString("desc")
        image = arguments?.getInt("image")

        binding.imgDetailedPostImage.setImageResource(image!!)
        binding.txtDetailedPostTitle.text = title
        binding.txtDetailedPostDescription.text = description

        displayMetrics = resources.displayMetrics

        setImageViewHeightAndWidth()

        return binding.root
    }

    private fun setImageViewHeightAndWidth() {

        imageViewWidth = displayMetrics.widthPixels.toDouble()

        imageHeight = binding.imgDetailedPostImage.drawable.intrinsicHeight.toDouble()
        imageWidth = binding.imgDetailedPostImage.drawable.intrinsicWidth.toDouble()

        ratioW = imageWidth / imageViewWidth
        ratioH = imageHeight / 1720

        setHeight = if (ratioW > ratioH) {
            imageHeight / ratioW
        } else {
            imageHeight / ratioH
        }

        if (setHeight <= 1720) {
            binding.imgDetailedPostImage.layoutParams.height = setHeight.toInt()
        } else {
            binding.imgDetailedPostImage.layoutParams.height = 1720
        }
    }
}