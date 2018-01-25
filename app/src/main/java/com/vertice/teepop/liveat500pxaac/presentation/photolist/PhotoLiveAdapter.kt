package com.vertice.teepop.liveat500pxaac.presentation.photolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.vertice.teepop.liveat500pxaac.R
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem
import com.vertice.teepop.liveat500pxaac.databinding.ListItemPhotoBinding
import kotlin.properties.Delegates

/**
 * Created by VerDev06 on 1/23/2018.
 */
class PhotoLiveAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_PHOTO_ITEM: Int = 1
    val TYPE_LOADING_ITEM: Int = 0

    var photoList: List<PhotoItem> by Delegates.observable(ArrayList(), { _, oldValue, newValue ->
        if (oldValue.isNotEmpty() && newValue.size > oldValue.size)
            notifyItemInserted(oldValue.size)
        else notifyDataSetChanged()
    })

    override fun getItemViewType(position: Int): Int {
        return if (position < photoList.size) TYPE_PHOTO_ITEM else TYPE_LOADING_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return if (viewType == TYPE_LOADING_ITEM) {
            ProgressViewHolder(layoutInflater.inflate(R.layout.list_item_loading, parent, false))
        } else {
            val binding = ListItemPhotoBinding.inflate(layoutInflater, parent, false)
            PhotoLiveViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (position < photoList.size)
            (holder as? PhotoLiveViewHolder)?.bindView(photoList[position])
    }

    class PhotoLiveViewHolder(val binding: ListItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(photoItem: PhotoItem?) {
            photoItem.let {
                binding.item = photoItem
            }
        }
    }

    class ProgressViewHolder(progressBar: View) : RecyclerView.ViewHolder(progressBar)
}