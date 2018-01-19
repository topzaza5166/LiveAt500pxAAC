package com.vertice.teepop.liveat500pxaac

import android.app.Application
import com.vertice.teepop.liveat500pxaac.data.dependency.RemoteDataModule
import com.vertice.teepop.liveat500pxaac.presentation.dependency.AppComponent
import com.vertice.teepop.liveat500pxaac.presentation.dependency.DaggerAppComponent

/**
 * Created by VerDev06 on 1/5/2018.
 */
class MainApplicaition : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    private val BASE_URL = "http://nuuneoi.com/courses/500px/"

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .remoteDataModule(RemoteDataModule(BASE_URL))
                .build()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}