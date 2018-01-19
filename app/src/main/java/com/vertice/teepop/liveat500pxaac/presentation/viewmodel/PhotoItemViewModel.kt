package com.vertice.teepop.liveat500pxaac.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vertice.teepop.liveat500pxaac.data.PhotoItemDataSource
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem
import com.vertice.teepop.liveat500pxaac.data.remote.ApiService
import javax.inject.Inject

/**
 * Created by VerDev06 on 1/19/2018.
 */
class PhotoItemViewModel : ViewModel() {

    @Inject
    lateinit var apiService: ApiService

    private val photoItemDataSource by lazy {
        PhotoItemDataSource(apiService)
    }

    private var photoItem: LiveData<PagedList<PhotoItem>>? = null

    fun getPhotoItem(): LiveData<PagedList<PhotoItem>> {
        photoItem = photoItem ?: LivePagedListBuilder<Int, PhotoItem>({ photoItemDataSource }, 20).build()
        return photoItem!!
    }


}