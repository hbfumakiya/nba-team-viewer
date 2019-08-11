package com.hardikfumakiya.thescoretest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hardikfumakiya.thescoretest.R
import com.hardikfumakiya.thescoretest.base.BaseActivity
import com.hardikfumakiya.thescoretest.model.Player

/**
 * Created by Hardik Fumakiya this class is used for displaying players of teams
 * **/
class PlayerListAdapter(private val activity: BaseActivity, private val textOptionList: ArrayList<Player>) :  RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)

		return PlayerViewHolder(itemView)
	}

	override fun getItemCount(): Int {
		return textOptionList.size
	}

	override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
		holder.bind(textOptionList[position])
	}
	// this is viewholder for recyclerview
	inner class PlayerViewHolder (view:View): RecyclerView.ViewHolder(view){
		fun bind(item: Player) = with(itemView) {
			with(itemView.findViewById<TextView>(R.id.playerName)) {
				text = "${item.firstName} ${item.lastName}"
			}
			with(itemView.findViewById<TextView>(R.id.playerPosition)) {
				text = "${activity.getText(R.string.position)} ${item.position}"
			}
			with(itemView.findViewById<TextView>(R.id.playerNumber)) {
				text = "${activity.getText(R.string.playerNumber)} ${item.number}"
			}
		}

	}

}