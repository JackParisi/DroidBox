package com.github.jackparisi.droidboxsample.core

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.jackparisi.droidboxsample.BuildConfig
import com.github.jackparisi.droidboxsample.networking.NetworkService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Giacomo Parisi on 24/10/17.
 * https://github.com/JackParisi
 */

@Module
class DroidBoxSampleModule(private val application: DroidBoxSampleApplication) {

    @Provides
    @Singleton
    fun application(): DroidBoxSampleApplication = application

    @Provides
    @Singleton
    fun gson(): Gson = Gson()

    @Provides
    fun networkService(gson: Gson): NetworkService {

        val httpClient = OkHttpClient.Builder()

        // Add logging interceptor only for debug build
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        // Setup http client's timeout
        httpClient.connectTimeout(40, TimeUnit.SECONDS)
        httpClient.writeTimeout(40, TimeUnit.SECONDS)
        httpClient.readTimeout(40, TimeUnit.SECONDS)

        // Create the retrofit NetworkService
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .baseUrl("http://api.steampowered.com/")
                .build()

        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    fun sharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}