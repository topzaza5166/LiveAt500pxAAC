package com.vertice.teepop.liveat500pxaac.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vertice.teepop.liveat500pxaac.data.PhotoItemRepository
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem
import javax.inject.Inject

/**
 * Created by VerDev06 on 1/19/2018.
 */
class PhotoItemViewModel : ViewModel() {

    @Inject
    lateinit var repository: PhotoItemRepository

    private var photoItem: LiveData<PagedList<PhotoItem>>? = null

    private var livePhotoItem: MutableLiveData<List<PhotoItem>>? = null

    fun getPageListPhotoItem(): LiveData<PagedList<PhotoItem>> {
        photoItem = photoItem ?: LivePagedListBuilder<Int, PhotoItem>({ repository.getPhotoItemDataSource() }, 20).build()
        return photoItem!!
    }

    fun getLivePhotoItem(): LiveData<List<PhotoItem>> {
        livePhotoItem = livePhotoItem ?: repository.getPhotoItemLiveData()
        return livePhotoItem!!
    }

    fun loadMore() {
        livePhotoItem?.value?.last()?.let {
            repository.updatePhotoItemLiveData(it.id, livePhotoItem!!)
        }
    }

    fun reloadLivePhotoItem() {
        livePhotoItem?.let {
            repository.getPhotoItemLiveData(livePhotoItem!!)
        }
    }

}