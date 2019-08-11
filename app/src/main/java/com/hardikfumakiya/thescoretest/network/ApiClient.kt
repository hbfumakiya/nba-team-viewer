package com.hardikfumakiya.thescoretest.network


import com.google.gson.GsonBuilder
import com.hardikfumakiya.thescoretest.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Hardik Fumakiya this class is defining Retrofit client using okhttp
 * **/
object ApiClient {
	@JvmStatic
	val API_SCORE: ApiScore=getRetroClient().create(ApiScore::class.java)

    private fun getRetroClient(): Retrofit {

        val gson = GsonBuilder()
					.setLenient()
					.create()

		return synchronized(ApiClient::class.java, Retrofit.Builder()
					.baseUrl(BASE_URL)
					.client(HttpClient.getHttpClient())
					.addConverterFactory(GsonConverterFactory.create(gson))
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())::build)
    }


}


