package com.vertice.teepop.liveat500pxaac.data.dependency

import com.vertice.teepop.liveat500pxaac.data.PhotoItemRepository
import com.vertice.teepop.liveat500pxaac.data.PhotoItemRepositoryImpl
import com.vertice.teepop.liveat500pxaac.data.remote.ApiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by VerDev06 on 1/23/2018.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoItemRepository(apiService: ApiService): PhotoItemRepository = PhotoItemRepositoryImpl(apiService, Schedulers.io())

}