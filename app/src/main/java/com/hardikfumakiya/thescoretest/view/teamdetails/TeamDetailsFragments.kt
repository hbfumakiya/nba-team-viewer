package com.hardikfumakiya.thescoretest.view.teamdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardikfumakiya.thescoretest.R
import com.hardikfumakiya.thescoretest.adapters.PlayerListAdapter
import com.hardikfumakiya.thescoretest.base.BaseActivity
import com.hardikfumakiya.thescoretest.base.BaseFragment
import com.hardikfumakiya.thescoretest.base.MarginItemDecoration
import com.hardikfumakiya.thescoretest.model.Player
import com.hardikfumakiya.thescoretest.model.Team


/**
 * Created by Hardik Fumakiya this fragment consist team details with players
 * **/
class TeamDetailsFragments : BaseFragment() {

    lateinit var team:Team

    private lateinit var playerList: RecyclerView

    private lateinit var playerListAdapter: PlayerListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_teamdetails, container, false)

        playerList=view.findViewById(R.id.playerList)

        team=arguments!!.getSerializable("team") as Team

        (activity as BaseActivity).title = "${team.name} (${team.players.size})"

        if(team.players.isNotEmpty()){
            playerListAdapter = PlayerListAdapter(activity as BaseActivity,team.players as ArrayList<Player>)

            val mLayoutManager = LinearLayoutManager(context)
            playerList.layoutManager = mLayoutManager
            playerList.itemAnimator = DefaultItemAnimator()
            playerList.setHasFixedSize(true)
            playerList.adapter = playerListAdapter
            playerList.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.normal_margin).toInt()))
        }else{
            toast(R.string.no_data_found)
        }

        return view
    }

}