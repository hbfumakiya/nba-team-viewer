package com.hardikfumakiya.thescoretest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Hardik Fumakiya this class is used for serializing teams data
 * **/
open class Team(
    @SerializedName("id") var id: Int,
    @SerializedName("full_name") var name: String,
    @SerializedName("wins") var wins: Int,
    @SerializedName("losses") var losses: Int,
    @SerializedName("players") var players: List<Player>) : Serializable {

    override fun toString(): String {
        return "Team(id=$id, name='$name', wins=$wins, losses=$losses, players=$players)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Team) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (wins != other.wins) return false
        if (losses != other.losses) return false
        if (players != other.players) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + wins
        result = 31 * result + losses
        result = 31 * result + players.hashCode()
        return result
    }


}