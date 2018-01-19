package com.vertice.teepop.liveat500pxaac.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by VerDev06 on 1/19/2018.
 */
object DataBinding {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
    }
}