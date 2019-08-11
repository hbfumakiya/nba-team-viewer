package com.hardikfumakiya.thescoretest.view.teamlist

import android.os.Bundle
import android.view.*
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardikfumakiya.thescoretest.R
import com.hardikfumakiya.thescoretest.adapters.TeamListAdapter
import com.hardikfumakiya.thescoretest.base.BaseActivity
import com.hardikfumakiya.thescoretest.base.BaseFragment
import com.hardikfumakiya.thescoretest.base.MarginItemDecoration
import com.hardikfumakiya.thescoretest.model.Team
import com.hardikfumakiya.thescoretest.sorting.ListSortOrder
import com.hardikfumakiya.thescoretest.sorting.ListSortViewModel
import com.hardikfumakiya.thescoretest.sorting.SortListType
import com.hardikfumakiya.thescoretest.viewmodels.TeamViewModel
import kotlinx.android.synthetic.main.fragment_teamlist.*

/**
 * Created by Hardik Fumakiya this activity consist fragment for displaying teams with wins and losses history
 * with different sorting type
 * **/
@VisibleForTesting
class TeamListFragments :BaseFragment() {

    private lateinit var teamViewModel: TeamViewModel

    private lateinit var listSortViewModel: ListSortViewModel

    private lateinit var teamList:RecyclerView

    private lateinit var listofTeams:MutableList<Team>

    private lateinit var teamListAdapter: TeamListAdapter

    private var listSortOrder= ListSortOrder.ASC

    private var listSort= SortListType.NAME


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teamlist, container, false)

        teamList=view.findViewById(R.id.teamList)

        setHasOptionsMenu(true)

        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try{

            teamViewModel=ViewModelProviders.of(this).get(TeamViewModel::class.java)

            teamViewModel.result.observe(this, Observer { result: List<Team> ->
                if(result.isNotEmpty()){
                    listofTeams=result.toMutableList()
                    showList()
                }else{
                    toast(R.string.apiError)
                }
            })

            listSortViewModel=ViewModelProviders.of(activity!!).get(ListSortViewModel::class.java)
            listSortViewModel.result.observe(this, Observer { sortedResult: MutableList<Team> ->
                listofTeams=sortedResult
                teamListAdapter.updateList(listofTeams)
            })
            changeOrder.text=listSortOrder.toString()
            changeOrder.setOnClickListener {
                when(listSortOrder){
                    ListSortOrder.ASC->{
                        listSortOrder=ListSortOrder.DESC

                        when (listSort) {
                            SortListType.WIN -> listSortViewModel.getTeamSortbyWin(listSortOrder,listofTeams)
                            SortListType.LOSS -> listSortViewModel.getTeamSortbyLoss(listSortOrder,listofTeams)
                            else -> listSortViewModel.getTeamSortbyName(listSortOrder,listofTeams)
                        }

                    }
                    ListSortOrder.DESC->{
                        listSortOrder=ListSortOrder.ASC

                        when (listSort) {
                            SortListType.WIN -> listSortViewModel.getTeamSortbyWin(listSortOrder,listofTeams)
                            SortListType.LOSS -> listSortViewModel.getTeamSortbyLoss(listSortOrder,listofTeams)
                            else -> listSortViewModel.getTeamSortbyName(listSortOrder,listofTeams)
                        }

                    }
                }
                changeOrder.text=listSortOrder.toString()

            }
            teamViewModel.getTeamList()

        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun showList() {
        if(listofTeams.isNotEmpty()){
            teamListAdapter = TeamListAdapter(activity as BaseActivity, listofTeams)

            val mLayoutManager = LinearLayoutManager(context)
            teamList.layoutManager = mLayoutManager
            teamList.itemAnimator = DefaultItemAnimator()
            teamList.setHasFixedSize(true)
            teamList.adapter = teamListAdapter
            teamList.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.normal_margin).toInt()))

            listSortViewModel.getTeamSortbyName(listSortOrder,listofTeams)
        }else{
            toast(R.string.no_data_found)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.sortbyWins -> {
                listSort= SortListType.WIN
                sortOrder.text=getText(R.string.sortbyWins)
                listSortOrder=ListSortOrder.ASC
                listSortViewModel.getTeamSortbyWin(listSortOrder,listofTeams)
                changeOrder.text=listSortOrder.toString()
                true
            }
            R.id.sortbyLosses ->{
                listSort= SortListType.LOSS
                sortOrder.text=getText(R.string.sortbyLosses)
                listSortOrder=ListSortOrder.ASC
                listSortViewModel.getTeamSortbyLoss(listSortOrder,listofTeams)
                changeOrder.text=listSortOrder.toString()
                true
            }
            R.id.sortbyNames->{
                listSort= SortListType.NAME
                sortOrder.text=getText(R.string.sortbyNames)
                listSortOrder=ListSortOrder.ASC
                listSortViewModel.getTeamSortbyName(listSortOrder,listofTeams)
                changeOrder.text=listSortOrder.toString()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }

}