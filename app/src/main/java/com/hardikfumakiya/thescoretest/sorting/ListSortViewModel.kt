package com.hardikfumakiya.thescoretest.sorting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hardikfumakiya.thescoretest.model.Team
import timber.log.Timber

/**
 * Created by Hardik Fumakiya this viewmodel class where sorting and data validation is being performed
 * **/
class ListSortViewModel(app: Application) : AndroidViewModel(app) {

    var result = MutableLiveData<MutableList<Team>>()


    fun isTeamValid(teams:MutableList<Team>):Boolean{
        teams.forEach { team->
            when {
                team.name.isEmpty() -> return false
                team.players.isEmpty() -> return false
                team.wins<0 -> return false
                team.losses<0 -> return false
                team.id<0 -> return false
            }
        }
        return true

    }

    fun getTeamSortbyWin(order:ListSortOrder, teams:MutableList<Team>)= try {
        if(isTeamValid(teams)){
            result.value = when(order){
                ListSortOrder.ASC->{
                    teams.sortedBy { it.wins }.toMutableList()
                }
                ListSortOrder.DESC->{
                    teams.sortedByDescending { it.wins }.toMutableList()
                }
            }
        }else{
            result.value= mutableListOf()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        Timber.d("getTeamSortedbyWins()")
    }

    fun getTeamSortbyLoss(order:ListSortOrder,teams:MutableList<Team>)= try {
        if(isTeamValid(teams)){
            result.value = when(order){
                ListSortOrder.ASC->{
                    teams.sortedBy { it.losses }.toMutableList()
                }
                ListSortOrder.DESC->{
                    teams.sortedByDescending { it.losses }.toMutableList()
                }
            }
        }else{
            result.value= mutableListOf()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        Timber.d("getTeamSortedbyLosses()")
    }

    fun getTeamSortbyName(order:ListSortOrder,teams:MutableList<Team>)= try {
        if(isTeamValid(teams)){
            result.value = when(order){
                ListSortOrder.ASC->{
                    teams.sortedBy { it.name }.toMutableList()
                }
                ListSortOrder.DESC->{
                    teams.sortedByDescending { it.name }.toMutableList()
                }
            }
        }else{
            result.value= mutableListOf()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        Timber.d("getTeamSortbyName()")
    }


}
