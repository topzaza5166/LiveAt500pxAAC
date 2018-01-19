package com.vertice.teepop.liveat500pxaac.presentation.photolist

import android.app.Activity
import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem
import com.vertice.teepop.liveat500pxaac.databinding.ListItemPhotoBinding


/**
 * Created by VerDev06 on 1/19/2018.
 */
class PhotoListAdapter(val context: Activity) : PagedListAdapter<PhotoItem, PhotoListAdapter.PhotoItemViewHolder>(DIFF_CALLBACK) {

    private val dm = DisplayMetrics().also {
        context.windowManager.defaultDisplay.getMetrics(it)
    }

    val height: Int by lazy {
        dm.widthPixels * 2 / 3
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoItemViewHolder {
        val binding = ListItemPhotoBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return PhotoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder?, position: Int) {
        holder?.bindView(getItem(position), height)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffCallback<PhotoItem>() {
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
                    oldItem == newItem
        }
    }


    class PhotoItemViewHolder(val binding: ListItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(photoItem: PhotoItem?, height: Int) {
            binding.relativeLayout.layoutParams = RelativeLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)

            photoItem.let {
                binding.item = photoItem
            }
        }

    }
}