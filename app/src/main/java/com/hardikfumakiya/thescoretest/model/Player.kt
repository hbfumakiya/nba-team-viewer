package com.hardikfumakiya.thescoretest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Hardik Fumakiya this class is used for serializing player data
 * **/
open class Player(
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("position") var position: String,
    @SerializedName("number") var number: Int) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (position != other.position) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + number
        return result
    }

    override fun toString(): String {
        return "Player(firstName='$firstName', lastName='$lastName', position='$position', number=$number)"
    }
}