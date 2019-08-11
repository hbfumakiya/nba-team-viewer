package com.hardikfumakiya.thescoretest.network

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Hardik Fumakiya this class is used to create client for network call with network cache
 * **/
class HttpClient private constructor() {

    val cacheSize = (10 * 1024 * 1024).toLong()
    val myCache= Cache(File("/data/data/com.hardikfumakiya.thescoretest/cache"), cacheSize)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
//                request = if (NetworkUtil.isConnected(context))
//                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)

            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()


    companion object {

        fun get(): HttpClient {

            return synchronized(HttpClient::class.java) {
                HttpClient()
            }
        }

        fun getHttpClient(): OkHttpClient {
            return get().okHttpClient
        }
    }
}
