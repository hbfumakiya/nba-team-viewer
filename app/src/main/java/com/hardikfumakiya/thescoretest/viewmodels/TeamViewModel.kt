package com.hardikfumakiya.thescoretest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hardikfumakiya.thescoretest.model.Team
import com.hardikfumakiya.thescoretest.network.ApiWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Hardik Fumakiya this viewmodel class where data is being fetched from network
 * **/

class TeamViewModel( app: Application) : AndroidViewModel(app) {

    lateinit var apiWrapper: ApiWrapper


    var result = MutableLiveData<List<Team>>()

    fun getTeamList()= try {
        apiWrapper= ApiWrapper()
        apiWrapper.getTeamList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: List<Team> ->
                result.value = t
            })
            { throwable ->
                Timber.e("error-> $throwable")

                throwable.printStackTrace()
            }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        Timber.d("getTeamList()")
    }!!
}
