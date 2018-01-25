package com.vertice.teepop.liveat500pxaac.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vertice.teepop.liveat500pxaac.data.datasource.PhotoItemDataSource
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem

/**
 * Created by VerDev06 on 1/5/2018.
 */
interface PhotoItemRepository {

    fun getPhotoItemDataSource(): PhotoItemDataSource

    fun getPhotoItemLiveData(): MutableLiveData<List<PhotoItem>>

    fun getPhotoItemLiveData(photoItemList: MutableLiveData<List<PhotoItem>>)

    fun updatePhotoItemLiveData(id: Int, photoItemList: MutableLiveData<List<PhotoItem>>)
}