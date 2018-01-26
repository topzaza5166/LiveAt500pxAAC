package com.vertice.teepop.liveat500pxaac.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vertice.teepop.liveat500pxaac.data.datasource.PhotoItemDataSource
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem
import com.vertice.teepop.liveat500pxaac.data.remote.ApiService
import io.reactivex.Scheduler

/**
 * Created by VerDev06 on 1/23/2018.
 */
class PhotoItemRepositoryImpl(private val apiService: ApiService, private val scheduler: Scheduler) : PhotoItemRepository {

    override fun loadPhotoItemBeforeId(id: Int): MutableLiveData<List<PhotoItem>> {
        val photoList: MutableLiveData<List<PhotoItem>> = MutableLiveData()
        apiService.loadPhotoListBeforeId(id)
                .subscribeOn(scheduler)
                .subscribe({ photoItemCollation ->
                    if (photoItemCollation.success) {
                        photoList.postValue(photoItemCollation.data)
                    }
                })
        return photoList
    }

    override fun getPhotoItemLiveData(): MutableLiveData<List<PhotoItem>> {
        val photoList: MutableLiveData<List<PhotoItem>> = MutableLiveData()
        apiService.loadPhotoList()
                .subscribeOn(scheduler)
                .subscribe({ photoItemCollation ->
                    if (photoItemCollation.success)
                        photoList.postValue(photoItemCollation.data)
                })
        return photoList
    }

    override fun getPhotoItemDataSource(): PhotoItemDataSource {
        return PhotoItemDataSource(apiService)
    }


}