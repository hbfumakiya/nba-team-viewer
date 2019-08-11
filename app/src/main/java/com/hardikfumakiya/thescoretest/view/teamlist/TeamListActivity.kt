package com.hardikfumakiya.thescoretest.view.teamlist

import android.os.Bundle
import com.hardikfumakiya.thescoretest.R
import com.hardikfumakiya.thescoretest.base.BaseActivity

/**
 * Created by Hardik Fumakiya this activity consist fragment for displaying teams with win and loss history
 * **/
class TeamListActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, TeamListFragments(), "TeamListFragments")
                    .commitAllowingStateLoss()
         }
    }

}