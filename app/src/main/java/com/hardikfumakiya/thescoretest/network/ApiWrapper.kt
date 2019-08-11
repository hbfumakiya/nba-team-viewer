package com.hardikfumakiya.thescoretest.network

import com.hardikfumakiya.thescoretest.model.Team
import io.reactivex.Single

/**
 * Created by Hardik Fumakiya this class is Apiwrapper class for network calls
 * **/
class ApiWrapper : InterfaceImpl {

    override fun getTeamList(): Single<List<Team>> {
        return ApiInterfaceImpl(ApiClient.API_SCORE).getTeamList()
    }

}
