package com.hardikfumakiya.thescoretest.view.teamdetails

import android.os.Bundle
import com.hardikfumakiya.thescoretest.R
import com.hardikfumakiya.thescoretest.base.BaseActivity

/**
 * Created by Hardik Fumakiya this activity consist fragment for displaying team details with players
 * **/
class TeamDetailActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val fragment=TeamDetailsFragments()
            val args= Bundle()
            args.putSerializable("team",intent.getSerializableExtra("team"))
            fragment.arguments=args

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, "TeamDetailsFragments")
                .commitAllowingStateLoss()
        }
    }
}