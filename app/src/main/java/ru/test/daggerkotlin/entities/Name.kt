package ru.test.daggerkotlin.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Name @JvmOverloads constructor(
    @SerializedName("first")
    var name: String = "name",
    @SerializedName("last")
    var lastName: String = "lastName"
) {
    override fun toString(): String {
        return "$name $lastName"
    }
}