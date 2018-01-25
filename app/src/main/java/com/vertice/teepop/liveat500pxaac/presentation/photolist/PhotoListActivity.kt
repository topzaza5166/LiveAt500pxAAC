package com.vertice.teepop.liveat500pxaac.presentation.photolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vertice.teepop.liveat500pxaac.R

class PhotoListActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

//        if (savedInstanceState == null)
//            supportFragmentManager.beginTransaction()
//                    .add(R.id.contentContainer, PhotoListFragment.newInstance())
//                    .commit()

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentContainer, LivePhotoListFragment.newInstance())
                    .commit()
    }
}
