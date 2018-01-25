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

    override fun updatePhotoItemLiveData(id: Int, photoItemList: MutableLiveData<List<PhotoItem>>) {
        apiService.loadPhotoListBeforeId(id)
                .subscribeOn(scheduler)
                .subscribe({ photoItemCollation ->
                    if (photoItemCollation.success) {
                        val list = photoItemList.value?.toMutableList() ?: ArrayList()
                        list.addAll(list.size, photoItemCollation.data)

                        photoItemList.postValue(list)
                    }
                })
    }

    override fun getPhotoItemLiveData(photoItemList: MutableLiveData<List<PhotoItem>>) {
        apiService.loadPhotoList()
                .subscribeOn(scheduler)
                .subscribe({ photoItemCollation ->
                    if (photoItemCollation.success)
                        photoItemList.postValue(photoItemCollation.data)
                })
    }

    override fun getPhotoItemLiveData(): MutableLiveData<List<PhotoItem>> {
        val photoList: MutableLiveData<List<PhotoItem>> = MutableLiveData()
        getPhotoItemLiveData(photoList)

        return photoList
    }

    override fun getPhotoItemDataSource(): PhotoItemDataSource {
        return PhotoItemDataSource(apiService)
    }


}