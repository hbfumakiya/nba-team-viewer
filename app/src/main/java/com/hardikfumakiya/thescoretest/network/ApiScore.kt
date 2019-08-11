package com.hardikfumakiya.thescoretest.network

import com.hardikfumakiya.thescoretest.model.Team
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Hardik Fumakiya this class is Api class for network calls
 * **/
interface ApiScore {
    @GET("master/input.json")
    fun getTeamList(): Single<List<Team>>
}










