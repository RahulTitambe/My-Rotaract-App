package com.admedia.newmyrotaract

import android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.transition.FragmentTransitionSupport
import com.admedia.newmyrotaract.databinding.CreateNewPostBinding
import com.admedia.newmyrotaract.dataclasses.Post
import com.admedia.newmyrotaract.fragments.CreateNewPostFragment
import com.admedia.newmyrotaract.fragments.PostsFragment

class MainActivity : AppCompatActivity() {

    lateinit var createNewPostFragment : CreateNewPostFragment
    var postsFragment = PostsFragment()
    var menuBtnCreateNewPost : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_app_logo)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary_pink)))

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
            .add(R.id.mainContainer, postsFragment, null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        menuBtnCreateNewPost = menu?.findItem(R.id.menuAddNewPost)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAddNewPost){

            createNewPostFragment = CreateNewPostFragment()
            createNewPostFragment.onSaveBtnClickListener = MyOnSaveClickListener()

            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                .replace(R.id.mainContainer, createNewPostFragment, null)
                .addToBackStack("CreateNewPostFragment")
                .commit()

            item.isVisible = false

            supportActionBar?.title = "Create New Post"
            supportActionBar?.setDisplayShowHomeEnabled(false)

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(menuBtnCreateNewPost?.isVisible == false) {

            EnableActionMenu()

            supportFragmentManager.beginTransaction()
                .remove(createNewPostFragment)
                .commit()
        }
    }
    inner class MyOnSaveClickListener : CreateNewPostFragment.OnSaveBtnClickListener{
        override fun onSave(post: Post) {
            EnableActionMenu()
            supportFragmentManager.beginTransaction()
                .remove(createNewPostFragment)
                .commit()
        }
    }

    fun EnableActionMenu(){
        menuBtnCreateNewPost?.isVisible = true
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "My Rotaract"
    }
}