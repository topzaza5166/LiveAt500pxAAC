package com.vertice.teepop.liveat500pxaac.presentation.dependency

import com.vertice.teepop.liveat500pxaac.data.dependency.RemoteDataModule
import com.vertice.teepop.liveat500pxaac.presentation.viewmodel.PhotoItemViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by VerDev06 on 1/19/2018.
 */
@Singleton
@Component(
        modules = arrayOf(
                RemoteDataModule::class
        )
)
interface AppComponent {

    fun inject(photoItemViewModel: PhotoItemViewModel)

}