package com.example.appforofficers.app

import android.content.Context
import com.example.database.MainDatabase
import com.example.database.mainDatabase
import com.example.repository.Repository
import com.example.serverapi.ServerApi
import com.example.serverapi.serverApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): MainDatabase {
        return mainDatabase(context)
    }

    @Provides
    @Singleton
    fun provideServerApi(): ServerApi {
        return serverApi(
            baseUrl = "http://10.0.2.2:8080",
            okHttpClient = provideOkhttp()
        )
    }

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}