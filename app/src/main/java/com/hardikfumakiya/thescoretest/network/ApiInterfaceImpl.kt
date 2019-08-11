package com.hardikfumakiya.thescoretest.network

import com.hardikfumakiya.thescoretest.model.Team
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by Hardik Fumakiya this class is used for interface of network api calls
 * **/

class ApiInterfaceImpl(private val apiScore: ApiScore) : InterfaceImpl {


	override fun getTeamList(): Single<List<Team>> {
		return apiScore.getTeamList()
	}
}
