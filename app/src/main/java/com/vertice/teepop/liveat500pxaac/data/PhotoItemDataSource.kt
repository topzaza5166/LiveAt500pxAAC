package com.vertice.teepop.liveat500pxaac.data

import android.arch.paging.ItemKeyedDataSource
import com.vertice.teepop.liveat500pxaac.data.model.PhotoItem
import com.vertice.teepop.liveat500pxaac.data.remote.ApiService
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by VerDev06 on 1/19/2018.
 */
class PhotoItemDataSource(val apiService: ApiService) : ItemKeyedDataSource<Int, PhotoItem>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<PhotoItem>) {
        apiService.loadPhotoListBeforeId(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({ photoItemCollection, _ ->
                    if (photoItemCollection.success)
                        callback.onResult(photoItemCollection.data)
                })
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<PhotoItem>) {
        apiService.loadPhotoList()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { photoItemCollection ->
                            if (photoItemCollection.success)
                                callback.onResult(photoItemCollection.data)
                        },
                        { error -> error.printStackTrace() }
                )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<PhotoItem>) {
        apiService.loadPhotoListAfterId(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe { photoItemCollection, _ ->
                    if (photoItemCollection.success)
                        callback.onResult(photoItemCollection.data)
                }
    }

    override fun getKey(item: PhotoItem): Int {
        return item.id
    }

}