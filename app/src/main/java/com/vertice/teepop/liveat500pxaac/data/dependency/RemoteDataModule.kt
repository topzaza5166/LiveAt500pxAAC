package com.vertice.teepop.liveat500pxaac.data.dependency

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vertice.teepop.liveat500pxaac.data.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by VerDev06 on 1/19/2018.
 */
@Module
class RemoteDataModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss-Z")
            .create()


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()

    @Provides
    @Singleton
    fun provideAPiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

}