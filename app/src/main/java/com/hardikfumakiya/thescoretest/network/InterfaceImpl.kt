package com.hardikfumakiya.thescoretest.network

import com.hardikfumakiya.thescoretest.model.Team
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by Hardik Fumakiya this class where imterface implementation is defined
 * **/
interface InterfaceImpl {
    fun getTeamList(): Single<List<Team>>
}
