package com.admedia.newmyrotaract.activities

import android.app.ActionBar
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.databinding.CreateNewPostBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File

class CreateNewPost : AppCompatActivity() {

    companion object{
        const val IMAGE_REQUEST_CODE = 100
    }

    private lateinit var binding : CreateNewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CreateNewPostBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setActionBar()
        listeners()

    }

    private fun setActionBar(){
        supportActionBar?.title = "Create New Post"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary_pink)))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {

        alertDialog()
        return super.onSupportNavigateUp()
    }

    private fun alertDialog(){

        MaterialAlertDialogBuilder(this)
            .setMessage("If you discard you will loose all the edits you've made?")
            .setTitle("Discard Edits?")
            .setPositiveButton("Discard", MyAlertPositiveButton())
            .setNeutralButton("Cancel", MyAlertNeutralButton())
            .setCancelable(true)
            .show()
    }

    private inner class MyAlertPositiveButton : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {
            finish()
        }
    }

    private inner class MyAlertNeutralButton : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {

        }
    }

    private fun listeners(){

        binding.imgPostImagePicker.setOnClickListener {
            val imageIntent = Intent(Intent.ACTION_PICK)
            imageIntent.type = "image/*"
            startActivityForResult(imageIntent, IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode== RESULT_OK) {
            binding.imgPostImagePicker.setImageURI(data?.data)

            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(this.contentResolver.openInputStream(data?.data!!), null, options )

            val imageWidth : Double = (options.outWidth).toDouble()
            val imageHeight : Double = (options.outHeight).toDouble()

            var setHeight = 0

            val imageViewWidth = binding.imgPostImagePicker.width

            val ratio_w = imageWidth/imageViewWidth
            val ratio_h = imageHeight/1720

            if(ratio_w > ratio_h){
                setHeight = (imageHeight/ratio_w).toInt()
            }
            else{
                setHeight = (imageHeight/ratio_h).toInt()
            }

            if(setHeight <= 1720){
                binding.imgPostImagePicker.layoutParams.height = setHeight
            }
            else{
                binding.imgPostImagePicker.layoutParams.height = 1720
            }
        }
    }

    override fun onBackPressed() {
        alertDialog()
    }
}