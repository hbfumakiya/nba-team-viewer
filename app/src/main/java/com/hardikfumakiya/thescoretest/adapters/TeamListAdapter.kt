package com.hardikfumakiya.thescoretest.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.hardikfumakiya.thescoretest.R
import com.hardikfumakiya.thescoretest.base.BaseActivity
import com.hardikfumakiya.thescoretest.model.Team
import com.hardikfumakiya.thescoretest.view.teamdetails.TeamDetailActivity

/**
 * Created by Hardik Fumakiya this class is used for displaying teams
 * **/
class TeamListAdapter(private val activity: BaseActivity, private val textOptionList: MutableList<Team>) :  RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {
	private var listofTeams = textOptionList
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)

		return TeamViewHolder(itemView)
	}

	override fun getItemCount(): Int {
		return listofTeams.size
	}

	override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
		holder.bind(listofTeams[position])
	}

	fun updateList(list: MutableList<Team>){
		listofTeams.clear()
		listofTeams =  list
		notifyDataSetChanged()
	}
// this is viewholder class of recyclerview
	inner class TeamViewHolder (view:View): RecyclerView.ViewHolder(view){
		fun bind(item: Team) = with(itemView) {
			with(itemView.findViewById<TextView>(R.id.teamName)) {
				text = "${item.name} (${activity.getText(R.string.players)} = ${item.players.size})"
			}
			with(itemView.findViewById<TextView>(R.id.teamWins)) {
				text = "${activity.getText(R.string.wins)} ${item.wins}"
			}
			with(itemView.findViewById<TextView>(R.id.teamLost)) {
				text = "${activity.getText(R.string.lost)} ${item.losses}"
			}
			setOnClickListener {
				val intent=Intent(activity, TeamDetailActivity::class.java)
				intent.putExtra("team",item)
				startActivity(activity,intent,null)

			}
		}

	}

}