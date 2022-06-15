package com.admedia.newmyrotaract.activities

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.admedia.newmyrotaract.R
import com.admedia.newmyrotaract.constants.Constants
import com.admedia.newmyrotaract.fragments.PostsFragment

class MainActivity : AppCompatActivity() {

    private var postsFragment = PostsFragment()
    private var menuBtnCreateNewPost: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_app_logo)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary_pink)))

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right
            )
            .add(R.id.mainContainer, postsFragment, null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        menuBtnCreateNewPost = menu?.findItem(R.id.menuAddNewPost)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAddNewPost) {

            val createNewPostIntent = Intent(this, CreateNewPost::class.java)
            startActivityForResult(createNewPostIntent, Constants.CreateNewPostKey)

        }

        return super.onOptionsItemSelected(item)
    }

}