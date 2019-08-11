package com.hardikfumakiya.thescoretest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hardikfumakiya.thescoretest.viewmodels.TeamViewModel

/**
 * Created by Hardik Fumakiya this is class where viewmodel is being created with lifecycle
 * **/
class ViewModelFactory(val app: ScoreApp) : ViewModelProvider.Factory {

    init {
        Injector.get().inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            return TeamViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}