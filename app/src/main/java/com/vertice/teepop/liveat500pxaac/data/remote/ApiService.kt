package com.vertice.teepop.liveat500pxaac.data.remote

import com.vertice.teepop.liveat500pxaac.data.model.PhotoItemCollection
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by VerDev06 on 1/19/2018.
 */
interface ApiService {

    @POST("list")
    fun loadPhotoList(): Single<PhotoItemCollection>

    @POST("list/before/{id}")
    fun loadPhotoListBeforeId(@Path("id") id: Int): Single<PhotoItemCollection>

    @POST("list/after/{id}")
    fun loadPhotoListAfterId(@Path("id") id: Int): Single<PhotoItemCollection>

}