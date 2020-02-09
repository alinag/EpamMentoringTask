package ru.test.daggerkotlin.entities

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class UserApiEntity (
//    @SerializedName("name")
//    val name: JsonArray,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("email")
    val email: String
)